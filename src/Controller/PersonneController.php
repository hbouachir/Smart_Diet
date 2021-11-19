<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Entity\Suivi;
use App\Form\PersonneFormType;
use App\Repository\PersonneRepository;
use App\Repository\SuiviRepository;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


class PersonneController extends AbstractController
{
    /**
     * @Route("/h", name="userhome")
     */
    public function home(Request $request,PersonneRepository $userRepository,SuiviRepository $suiviRepository): Response
    {

        return $this->render('admin/homeadmin.html.twig', ['c' => $userRepository->findAll(),'s' => $suiviRepository->findAll()

        ]);}
    /**
     * @Route("/", name="user")
     */
    public function logadmin(Request $request): Response
    {
        return $this->render('admin/loginadmin.html.twig', [

        ]);}

    /**
     * @Route("/personne", name="personne")
     */
    public function index(PersonneRepository $repo,Request $request): Response
    {
        return $this->render('personne/Affiche.html.twig', [
            'c' => $repo->findAll(),
        ]);


    }
    /**
     * @Route ("AFF/",name="A")
     */
    public function Afficher (PersonneRepository $repo){
       $user=$repo->findAll();
       return $this->render('personne/Affiche.html.twig',
            ['c'=>$user]);
    }

    /**
     * @Route ("Delete/{id}",name="D")
     */
    function remove($id,PersonneRepository  $repository){
        $user=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('userhome');

    }

    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajout/" ,name="Ajou")
     */
    function ajout (Request $request,UserPasswordEncoderInterface $passwordEncoder)
    {
        $user = new Personne();
        $form = $this->createForm(PersonneFormType::class, $user);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $user->setPassword($passwordEncoder->encodePassword($user,$form->get('password')->getData()));
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $this->addFlash('notice', 'Votre user à bien été ajouter !');
            return $this->redirectToRoute('userhome');

        }
        return $this->render("personne/Ajou.html.twig",
            ['f' => $form->createView()]);

    }

    /**
     * @param Request $request1
     * @return Response
     * @Route ("AjoutC/" ,name="AjouC")
     */
    function ajoutC (Request $request1,UserPasswordEncoderInterface $passwordEncoder){
        $client =new Personne();
        $form1=$this->createForm(PersonneFormType::class,$client);
        $form1->add('Ajouter',SubmitType::class);
        $form1->handleRequest($request1);
        if($form1->isSubmitted() &&$form1->isValid() ){
            $client->setPassword($passwordEncoder->encodePassword($client,$form1->get('password')->getData()));
            $em=$this->getDoctrine()->getManager();
            $em->persist($client);
            $em->flush();
            $this->addFlash('notice', 'Votre client à bien été ajouter !');
            return $this->redirectToRoute('A');

        }
        return $this->render("personne/AjouClient.html.twig",
            ['fc'=>$form1->createView()]);}

    /**
     * @Route ("Update/{id}",name="U")
     */
    function Update(Request $request,PersonneRepository $repository,$id){
        $user=$repository->find($id);
        $form=$this->createForm(PersonneFormType::class,$user);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
            return $this->redirectToRoute('A');

        }
        return $this->render("personne/Ajou.html.twig",
            ['f'=>$form->createView()]);

    }

    /**
     * @Route("/loginp", name="login")
     */
    public function loginAction (Request $request, PersonneRepository  $userRepository)
    {
        $user=new Personne();
        if ($request->isMethod('POST')) {
            // load the user in some way (e.g. using the form input)
            $emaill = $request->request->get('mail');
            $pw = $request->request->get('password');
            $user = $this->entityManager->getRepository(Personne::class)->findOneBy(['email' => $emaill]);
            if (!$user) {
                // fail authentication with a custom error
                throw new CustomUserMessageAuthenticationException('Email could not be found.');
            }else


            return $this->redirectToRoute('A');
        }

        return $this->render('security/login.html.twig', []);
        }

    /**
     * @Route("/loginpp", name="user_login", methods={"GET","POST"})
     */
    public function login(Request $request,PersonneRepository $userRepository,SuiviRepository $suiviRepository): Response
    {
        $session = $request->getSession();
        $session->clear();
        $user = new Personne();
        $suivi=new Suivi();
        $form = $this->createFormBuilder($user)
            ->add('privilege' , ChoiceType::class, array(
                'choices'  => array(
                    'Client' => 'client',
                    'Admin' => 'admin',
                    'Nutritionniste' => 'nutritionniste',
                    'Admin' => 'admin',)))


            ->add('mail', TextType::class,[
                'attr' => [
                    'placeholder' => 'Taper votre email',
                ],

            ])
            ->add('password', PasswordType::class,[
                'attr' => [
                    'placeholder' => 'Taper votre Password',
                ],

            ])

            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $password   = $user->getPassword();
            $privilege =$user->getPrivilege();
            $mail = $user->getMail();
            $user1 = $userRepository->findOneBy(array('mail'=>$mail,
                'password'=>$password,'privilege'=>$privilege));
            if (!$user1)
            {
                $this->get('session')->getFlashBag()->add('info',
                    'Login Incorrecte Vérifier Votre Email  ....');
            }
            else
            {
                if (!$session->has('id'))
                {$session->set('id',$user1->getId());
                    $id = $session->get('id');
                  $iduser =$suivi->getIduser($id);
                    if($privilege=='admin'){
                        # return $this->redirectToRoute('A');
                        return $this->render('personne/Affiche.html.twig', [
                            'id'=>$id  , 'c' => $userRepository->findAll()
                        ]);


                }elseif ($privilege=='nutritionniste'){
                        return $this->render('security/nut.html.twig', [
                            'id'=>$id  , 'c' => $suiviRepository->findAll()
                        ]);

                    }else{
                        return $this->render('security/client.html.twig', [

                        ]);

                    }
                   #return $this->redirectToRoute('AS');
                }
            }
        }

        return $this->render('security/login.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/loginad", name="admin_login", methods={"GET","POST"})
     */
    public function loginad(Request $request,PersonneRepository $userRepository,SuiviRepository $suiviRepository): Response
    {
        $session = $request->getSession();
        $session->clear();
        $user = new Personne();
        $suivi=new Suivi();
        $form = $this->createFormBuilder($user)
            ->add('privilege' , ChoiceType::class, array(
                'choices'  => array(
                    'Client' => 'client',
                    'Admin' => 'admin',
                    'Nutritionniste' => 'nutritionniste',
                    )))


            ->add('mail', TextType::class,[
                'attr' => [
                    'placeholder' => 'Taper votre email',
                ],

            ])
            ->add('password', PasswordType::class,[
                'attr' => [
                    'placeholder' => 'Taper votre Password',
                ],

            ])

            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $password   = $user->getPassword();
            $privilege =$user->getPrivilege();
            $mail = $user->getMail();
            $user1 = $userRepository->findOneBy(array('mail'=>$mail,
                'password'=>$password,'privilege'=>$privilege));
            if (!$user1)
            {
                $this->get('session')->getFlashBag()->add('info',
                    'Login Incorrecte Vérifier Votre Email  ....');
            }
            else
            {
                if (!$session->has('id'))
                {$session->set('id',$user1->getId());
                    $id = $session->get('id');
                    $iduser =$suivi->getIduser($id);
                    if($privilege=='admin'){
                        # return $this->redirectToRoute('A');
                        return $this->render('admin/homeadmin.html.twig', [
                            'id'=>$id  , 'c' => $userRepository->findAll(),'s' => $suiviRepository->findAll()
                        ]);


                    }elseif ($privilege=='nutritionniste'){
                        return $this->render('security/nut.html.twig', [
                            'id'=>$id  , 'c' => $suiviRepository->findAll()
                        ]);

                    }else{
                        return $this->render('security/client.html.twig', [

                        ]);

                    }

                }
            }
        }

        return $this->render('admin/loginadmin.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/NVPW", name="NVPW", methods={"GET"})
     */
    public function inde(): Response
    {
        return $this->render('personne/nouvpw.html.twig', [

        ]);
    }
}
