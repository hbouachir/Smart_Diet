<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Entity\Suivi;
use App\Form\ChangePasswordProfilType;
use App\Form\ChangePasswordType;
use App\Form\Clientprofil;

use App\Form\ClientupadForm;
use App\Form\NutForm;
use App\Form\NutupadForm;
use App\Form\PersonneFormType;
use App\Form\PersonneFormType1;
use App\Form\profilnutType;
use App\Form\RechType;
use App\Repository\PersonneRepository;
use App\Repository\produitRepository;
use App\Repository\SuiviRepository;

use Doctrine\Persistence\ObjectManager;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Core\Exception\CustomUserMessageAuthenticationException;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Mime\Email;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;


class PersonneController extends AbstractController
{

    private $token;

    public function __construct(TokenInterface $token = null)
    {

        $this->token = $token;
    }

    /**
     * @Route("/h", name="userhome")
     */
    public function home(Request $request, PersonneRepository $userRepository, SuiviRepository $suiviRepository): Response
    {

        $user = new Personne();
        $userInfo = ['nom' => null];

        $form = $this->createForm(RechType::class, $userInfo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $userInfo = $form->getData();
            $term = $userInfo['nom'];

            $user = $userRepository->search($term);
            if ($user === null) {
                $this->addFlash('danger', 'Invalid username');
                return $this->render('admin/homeadmin.html.twig', [
                    'utilisateur' => $user,
                    'form' => $form->createView(),
                    'message' => "nom n'existe pas, merci de verifier"
                ]);
            }

           // $user=$userRepository->findOneBy(['nom'=>$term]);
            return $this->render('admin/homeadmin.html.twig', ['c' => $user, 's' => $suiviRepository->findAll(),'form'=>$form->createView(),

            ]);


        }

        return $this->render('admin/homeadmin.html.twig', ['c' => $userRepository->findAll(), 's' => $suiviRepository->findAll(),'form'=>$form->createView(),

        ]);
    }


    /**
     * @Route("/lp", name="user")
     */
    public function logadmin(Request $request): Response
    {
        return $this->render('admin/loginadmin.html.twig', [

        ]);
    }

    /**
     * @Route("/personne", name="personne")
     */
    public function index(PersonneRepository $repo, Request $request): Response
    {
        return $this->render('personne/Affiche.html.twig', [
            'c' => $repo->findAll(),
        ]);


    }
    /**
     * @Route("/suivinutc", name="home_nut")
     */
    public function indexnut(PersonneRepository $repo,Request $request): Response
    {$value='client';
        $user = new Personne();
        $userInfo = ['nom' => null];

        $form = $this->createForm(RechType::class, $userInfo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $userInfo = $form->getData();
            $term = $userInfo['nom'];

            $user = $repo->search($term);

            // $user=$userRepository->findOneBy(['nom'=>$term]);
            return $this->render('security/nut.html.twig', ['n' => $user,'form'=>$form->createView(),

            ]);


        }
        return $this->render('security/nut.html.twig', [
            'n' => $repo->searchClient($value),'form'=>$form->createView(),
        ]);


    }

    /**
     * @Route("/app", name="app")
     */
    public function app(Request $request): Response
    {
        return $this->render('/base.html.twig', [

        ]);


    }

    /**
     * @Route ("AFF/",name="A")
     */
    public function Afficher(PersonneRepository $repo)
    {
        $user = $repo->findAll();
        return $this->render('personne/Affiche.html.twig',
            ['c' => $user]);
    }

    /**
     * @Route ("Delete/{id}",name="D")
     */
    function remove($id, PersonneRepository $repository)
    {
        $user = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('userhome');

    }

    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajout/" ,name="Ajou")
     */
    function ajout(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        $user = new Personne();
        $form = $this->createForm(PersonneFormType::class, $user);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $user->setPassword($passwordEncoder->encodePassword($user, $form->get('password')->getData()));
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
    function ajoutC(Request $request1, UserPasswordEncoderInterface $passwordEncoder, PersonneRepository $utilisateurRepository, MailerInterface $mailer)
    {
        $client = new Personne();
        $form1 = $this->createForm(PersonneFormType1::class, $client);
        $form1->add('Ajouter', SubmitType::class);
        $form1->handleRequest($request1);
        if ($form1->isSubmitted() && $form1->isValid()) {
            if ($utilisateurRepository->findOneByEmail($client->getMail()) != null) {// s'il ne l'est pas, on renvoie vers la page de création d'utilisateur avec une notification
                return $this->render('personne/AjouClient.html.twig', [
                    'utilisateur' => $client,
                    'fc' => $form1->createView(),
                    'message' => "Email est déjà utilisé, merci de changer email"
                ]);
            }
            $client->setPassword($passwordEncoder->encodePassword($client, $form1->get('password')->getData()));
            $em = $this->getDoctrine()->getManager();
            $em->persist($client);
            $em->flush();
            $email = (new Email())
                ->from('sirina.belhassen@gmail.com')
                ->to($client->getMail())
                //->cc('cc@example.com')
                //->bcc('bcc@example.com')
                //->replyTo('fabien@example.com')
                //->priority(Email::PRIORITY_HIGH)
                ->subject('Inscription SmartDietAPP')
                ->text('Sending emails is fun again!')
                ->html('<html>
  <body>
    <p>Bonjour <br>
    Merci pour votre confiance  </p>
    <p><a href="http://127.0.0.1:8000/loginpp">Sign in </a> voila notre site</p>
  </body>
</html> ');

            $mailer->send($email);
            $this->addFlash('notice', 'Votre client à bien été ajouter !');
            return $this->redirectToRoute('user_login');

        }
        return $this->render("personne/AjouClient.html.twig",
            ['fc' => $form1->createView()]);
    }

    /**
     * @param Request $request2
     * @return Response
     * @Route ("AjoutN/" ,name="AjouN")
     */
    function ajoutN(Request $request2, UserPasswordEncoderInterface $passwordEncoder, PersonneRepository $utilisateurRepository, MailerInterface $mailer)
    {
        $client = new Personne();
        $form1 = $this->createForm(NutForm::class, $client);
        $form1->add('Ajouter', SubmitType::class);
        $form1->handleRequest($request2);
        if ($form1->isSubmitted() && $form1->isValid()) {
            if ($utilisateurRepository->findOneByEmail($client->getMail()) != null) {// s'il ne l'est pas, on renvoie vers la page de création d'utilisateur avec une notification
                return $this->render('personne/AjoutNut.html.twig', [
                    'utilisateur' => $client,
                    'fc' => $form1->createView(),
                    'message' => "Email est déjà utilisé, merci de changer email"
                ]);
            }
            $client->setPassword($passwordEncoder->encodePassword($client, $form1->get('password')->getData()));
            $em = $this->getDoctrine()->getManager();
            $em->persist($client);
            $em->flush();
            $email = (new Email())
                ->from('sirina.belhassen@gmail.com')
                ->to($client->getMail())
                //->cc('cc@example.com')
                //->bcc('bcc@example.com')
                //->replyTo('fabien@example.com')
                //->priority(Email::PRIORITY_HIGH)
                ->subject('Inscription SmartDietAPP')
                ->text('Sending emails is fun again!')
                ->html('<p>Votre render vous est confirmé! merciiiii </p> ');

            $mailer->send($email);
            $this->addFlash('notice', 'Votre client à bien été ajouter !');
            return $this->redirectToRoute('userhome');

        }
        return $this->render("personne/AjoutNut.html.twig",
            ['fc' => $form1->createView()]);
    }

    /**
     * @Route ("Update/{id}",name="U")
     */
    function Update(Request $request, PersonneRepository $repository, $id)
    {
        $user = $repository->find($id);
        $form = $this->createForm(PersonneFormType::class, $user);
        $form->add('Update', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();

            return $this->redirectToRoute('userhome');

        }
        return $this->render("personne/Ajou.html.twig",
            ['f' => $form->createView()]);

    }

    /**
     * @Route("/loginp", name="login")
     */
    public function loginAction(Request $request, PersonneRepository $userRepository)
    {
        $user = new Personne();
        if ($request->isMethod('POST')) {
            // load the user in some way (e.g. using the form input)
            $emaill = $request->request->get('mail');
            $pw = $request->request->get('password');
            # $user = $this->entityManager->getRepository(Personne::class)->findOneBy(['email' => $emaill]);
            if (!$user) {
                // fail authentication with a custom error
                throw new CustomUserMessageAuthenticationException('Email could not be found.');
            } else


                return $this->redirectToRoute('A');
        }

        return $this->render('security/login.html.twig', []);
    }

    /**
     * @Route("/loginpp", name="user_login", methods={"GET","POST"})
     */
    public function login(Request $request, PersonneRepository $userRepository, SuiviRepository $suiviRepository): Response
    {
        $session = $request->getSession();
        $session->clear();
        $user = new Personne();
        $suivi = new Suivi();
        $form = $this->createFormBuilder($user)
            ->add('mail', TextType::class, [
                'attr' => [
                    'placeholder' => 'Taper votre email',
                ],

            ])
            ->add('password', PasswordType::class, [
                'attr' => [
                    'placeholder' => 'Taper votre Password',
                ],

            ])
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $password = $user->getPassword();

            $mail = $user->getMail();
            $user1 = $userRepository->findOneBy(array('mail' => $mail,
                'password' => $password));

            if (!$user1) {
                $this->get('session')->getFlashBag()->add('info',
                    'Login Incorrecte Vérifier Votre Email  ....');
            } else {

                if (!$session->has('id', 'name')) {
                    $session->set('id', $user1->getId());
                    $session->set('name', $user1->getPrenom());
                    $id = $session->get('id');
                    $name = $session->get('name');
                    $privilege = $user1->getPrivilege();
                    $mod=$user1->getMode();
                    $token = $this->container->get('security.token_storage')->getToken();

                    # $user = $this->getDoctrine()->getRepository(Personne::class)->find($id);

                    if ($privilege == 'admin') {
                        # return $this->redirectToRoute('A');
                        return $this->redirectToRoute('user');


                    } elseif ($privilege == 'nutritionniste') {
                       // return $this->render('security/nut.html.twig', [
                       //     'id' => $id, 'u' => $userRepository->find($id),'n'=>$userRepository->findAll(),'form'=>$form->createView(),
                      //  ]);
                        return $this->redirectToRoute('home_nut');

                    } elseif($privilege == 'client' && $mod == null ) {
                        return $this->render('security/client.html.twig', ['u' => $userRepository->find($id)

                        ]);


                    }else{$this->get('session')->getFlashBag()->add('info2',
                        'utilisateur désactivé  ....');

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
    public function loginad(Request $request, PersonneRepository $userRepository, SuiviRepository $suiviRepository): Response
    {
        $session = $request->getSession();
        $session->clear();
        $user = new Personne();

        $form = $this->createFormBuilder($user)
            ->add('mail', TextType::class, [
                'attr' => [
                    'placeholder' => 'Taper votre email',
                ],

            ])
            ->add('password', PasswordType::class, [
                'attr' => [
                    'placeholder' => 'Taper votre Password',
                ],

            ])
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $password = $user->getPassword();

            $mail = $user->getMail();
            $user1 = $userRepository->findOneBy(array('mail' => $mail,
                'password' => $password));

            if (!$user1) {
                $this->get('session')->getFlashBag()->add('info',
                    'Login Incorrecte Vérifier Votre Email  ....');
            } else {
                if (!$session->has('id')) {
                    $session->set('id', $user1->getId());
                    $id = $session->get('id');
                    $privilege = $user1->getPrivilege();
                    if ($privilege == 'admin') {
                        # return $this->redirectToRoute('A');
                        return $this->redirectToRoute('profilA');



                    } else {
                        $this->get('session')->getFlashBag()->add('info1',
                            'cette interface pour admin seulement  ....');

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
     * @Route("/NVP", name="NVP")
     */
    public function forgot(Request $request, UserPasswordEncoderInterface $encoder, PersonneRepository $userRepository)
    {
        $user = new Personne();
        $userInfo = ['mail' => null, 'password' => null];

        $form = $this->createForm(ChangePasswordType::class, $userInfo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $userInfo = $form->getData();
            $username = $userInfo['mail'];
            $plainPassword = $userInfo['password'];

            $user = $userRepository->findOneBy(['mail' => $username]);
            if ($user === null) {
                $this->addFlash('danger', 'Invalid username');
                return $this->render('personne/npwlogin.html.twig', [
                    'utilisateur' => $user,
                    'form' => $form->createView(),
                    'message' => "Email n'existe pas, merci de verifier votre email"
                ]);
            }
            $password = $encoder->encodePassword($user, $plainPassword);

            $user->setPassword($password);
            $em = $this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();


            return $this->redirectToRoute('user_login');
        }

        return $this->render('personne/npwlogin.html.twig', array('form' => $form->createView()));
    }

    /**
     * @Route("/NVPC", name="NVPC")
     */
    public function changerpw(Request $request, UserPasswordEncoderInterface $encoder, PersonneRepository $userRepository)
    {$session = $request->getSession();
        $user = new Personne();
        $userInfo = ['password' => null];

        $form = $this->createForm(ChangePasswordProfilType::class, $userInfo);
        $form->handleRequest($request);
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);

            if ($form->isSubmitted() && $form->isValid()) {

                $userInfo = $form->getData();
                // $username = $userInfo['mail'];
                $plainPassword = $userInfo['password'];


                $password = $encoder->encodePassword($user, $plainPassword);

                $user->setPassword($password);
                $em = $this->getDoctrine()->getManager();
                //$em->persist($classroom);
                $em->flush();

                $request->getSession()->getFlashBag()->add('INFO', 'password Modifié avec succés');
                return $this->render('security/client.html.twig', ['u' => $userRepository->find($idd)

                ]);
            }
        }
        return $this->render('personne/npw.html.twig', array('form' => $form->createView()));
    }

    /**
     * @Route("/NVPN", name="NVPN")
     */
    public function changerpwN(Request $request, UserPasswordEncoderInterface $encoder, PersonneRepository $userRepository)
    {$session = $request->getSession();
        $user = new Personne();
        $userInfo = ['password' => null];

        $form = $this->createForm(ChangePasswordProfilType::class, $userInfo);
        $form->handleRequest($request);
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);

            if ($form->isSubmitted() && $form->isValid()) {

                $userInfo = $form->getData();
                // $username = $userInfo['mail'];
                $plainPassword = $userInfo['password'];


                $password = $encoder->encodePassword($user, $plainPassword);

                $user->setPassword($password);
                $em = $this->getDoctrine()->getManager();
                //$em->persist($classroom);
                $em->flush();

                $request->getSession()->getFlashBag()->add('INFO', 'password Modifié avec succés');
                return $this->redirectToRoute('home_nut');
            }
        }
        return $this->render('personne/npw.html.twig', array('form' => $form->createView()));
    }
    /**
     *
     * @return Response
     * @Route("/NVPW", name="NVPW")
     */
    function modpw(Request $request, PersonneRepository $utilisateurRepository, MailerInterface $mailer)
    {
        $user = new Personne();
        $form1 = $this->createFormBuilder($user)
            ->add('mail', TextType::class, [
                'attr' => [
                    'placeholder' => 'Taper votre email',
                ],

            ])
            ->getForm();

        $form1->handleRequest($request);
        $mail = $user->getMail();
        if ($form1->isSubmitted()) {

            if ($utilisateurRepository->findOneBy(array('mail' => $mail)) == null) {// s'il ne l'est pas, on renvoie vers la page de création d'utilisateur avec une notification
                return $this->render('personne/nouvpw.html.twig', [
                    'utilisateur' => $user,
                    'form' => $form1->createView(),
                    'message' => "Email nexiste pas, merci de verifier email"
                ]);
            } else {
                $email = (new Email())
                    ->from('sirina.belhassen@gmail.com')
                    ->to($user->getMail())
                    ->subject('password SmartDietAPP')
                    ->text('Sending emails is fun again!!!!!')
                    ->html(' <a href="http://127.0.0.1:8000/NVP/">lien</a> ');

                $mailer->send($email);

                return $this->redirectToRoute('user_login');
            }
        }
        return $this->render("personne/nouvpw.html.twig",
            ['user' => $user, 'form' => $form1->createView()]);
    }

    /**
     * @Route("/listef", name="facture_list", methods={"GET"})
     */
    public function listef(PersonneRepository $factureRepository, Request $request): Response
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $name = $session->get('id');
            $pdfOptions = new Options();
            $pdfOptions->set('defaultFont', 'Arial');

            // Instantiate Dompdf with our options
            $dompdf = new Dompdf($pdfOptions);
            $factures = $factureRepository->findAll();


            // Retrieve the HTML generated in our twig file
            $html = $this->renderView('default/mypdf.html.twig', ['id' => $name,
                'c' => $factures,
            ]);

            // Load HTML to Dompdf
            $dompdf->loadHtml($html);

            // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
            $dompdf->setPaper('A2', 'portrait');

            // Render the HTML as PDF
            $dompdf->render();
            // Output the generated PDF to Browser (force download)
            $dompdf->stream("mypdf.pdf", [
                "Attachment" => false
            ]);

            // Send some text response
            return new Response("The PDF file has been succesfully generated !");


        }
    }

    /**
     *
     * @Route ("profilC/" ,name="profilC")
     */
    function UpdateProfil(Request $request, PersonneRepository $userRepository)
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $form = $this->createForm(Clientprofil::class, $user);
            $form->add('Update', SubmitType::class);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $request->getSession()->getFlashBag()->add('Notice', 'Profil Modifié avec succés');
                $em->flush();
                return $this->render('security/client.html.twig', ['u' => $userRepository->find($idd)

                ]);

            }
            return $this->render("personne/Profil.html.twig",
                ['fc' => $form->createView()]);

        }
    }
    /**
     * @Route("/logout", name="logout")
     */
    public function connexion(Request $request)
    {
        //en cas de connexion ouverte
        $session = $request->getSession();
        if($session->has('id'))
        {
            //on la referme, ain de pouvoir initier une nouvelle connexion
            $session->remove('id');
            return $this->redirectToRoute('user_login');
        }
}

    /**
     * @Route("/loginnn", name="app_login")
     */
    public function loginnn(AuthenticationUtils $authenticationUtils): Response
    {
        // if ($this->getUser()) {
        //     return $this->redirectToRoute('target_path');
        // }

        // get the login error if there is one
        $error = $authenticationUtils->getLastAuthenticationError();
        // last username entered by the user
        $lastUsername = $authenticationUtils->getLastUsername();

        return $this->render('security/logintest.html.twig', ['last_username' => $lastUsername, 'error' => $error]);
    }
    /**
     * @Route("/logoutt", name="app_logout")
     */
    public function logoutt()
    {
        throw new \Exception('This method can be blank - it will be intercepted by the logout key on your firewall');
    }
    /**
     * @Route ("UpdateA/{id}",name="UA")
     */
    function UpdateA(Request $request, PersonneRepository $repository, $id)
    {
        $user = $repository->find($id);
        if($user->getPrivilege()=='nutritionniste'){
        $form = $this->createForm(NutupadForm::class, $user);
        $form->add('Update', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();

            return $this->redirectToRoute('userhome');

        }
            return $this->render("personne/AjoutNut.html.twig",
                ['fc' => $form->createView()]);
        }

        if($user->getPrivilege()=='client'){
            $form1 = $this->createForm(ClientupadForm::class, $user);
            $form1->add('Update', SubmitType::class);
            $form1->handleRequest($request);
            if ($form1->isSubmitted() && $form1->isValid()) {
                $em = $this->getDoctrine()->getManager();
                //$em->persist($classroom);
                $em->flush();

                return $this->redirectToRoute('userhome');

            }
            return $this->render("personne/AjoutNut.html.twig",
                ['fc' => $form1->createView()]);
        }



    }

    /**
     * @Route("/desactiver", name="des")
     */
    public function desactiver(Request $request)
    {
        //en cas de connexion ouverte
        $session = $request->getSession();
        if($session->has('id'))
        {$idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $user->setMode(1);
            $em = $this->getDoctrine()->getManager();

            $em->flush();

            //on la referme, ain de pouvoir initier une nouvelle connexion
            $session->remove('id');

            return $this->redirectToRoute('user_login');
        }
    }

    /**
     * @Route ("SHOW/{id}",name="SHOW")
     */
    function Show(Request $request, SuiviRepository $repo, $id){

        $suivi = new Suivi();

        $user = $this->getDoctrine()->getRepository(Personne::class)->find($id);
        $suivi->setIduser($user);
        $suivi = $repo->findBy(['iduser'=>$suivi->getIduser()]);
        return $this->render('suivi/listesuiviparclientnut.html.twig',
            ['c' => $suivi]);
    }

    /**
     *
     * @Route ("profilN/" ,name="profilN")
     */
    function UpdateProfilnut(Request $request, PersonneRepository $userRepository)
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $form = $this->createForm(profilnutType::class, $user);
            $form->add('Update', SubmitType::class);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $request->getSession()->getFlashBag()->add('Notice', 'Profil Modifié avec succés');
                $em->flush();
                return $this->redirectToRoute('home_nut');

            }
            return $this->render("personne/ProfilN.html.twig",
                ['fc' => $form->createView()]);

        }
    }
    /**
     * @Route ("activer/{id}",name="ACTIVER")
     */
    function activer(Request $request, PersonneRepository $repository, $id)
    {
        $user = $repository->find($id);
        $user->setMode(null);
        $em = $this->getDoctrine()->getManager();

        $em->flush();

        return $this->redirectToRoute('userhome');



    }

    /**
     * @Route ("deactiverad/{id}",name="DESACTIVERAD")
     */
    function desactiverad(Request $request, PersonneRepository $repository, $id)
    {
        $user = $repository->find($id);
        $user->setMode(1);
        $em = $this->getDoctrine()->getManager();

        $em->flush();

        return $this->redirectToRoute('userhome');



    }

    /**
     *
     * @Route ("profilA/" ,name="profilA")
     */
    function UpdateProfilad(Request $request, PersonneRepository $userRepository)
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);

            return $this->render('admin/profiladmin.html.twig', ['u' => $userRepository->find($idd)

            ]);

        }
    }
    /**
     * @Route("/logoutad", name="logoutad")
     */
    public function connexionad(Request $request)
    {
        //en cas de connexion ouverte
        $session = $request->getSession();
        if($session->has('id'))
        {
            //on la referme, ain de pouvoir initier une nouvelle connexion
            $session->remove('id');
            return $this->redirectToRoute('admin_login');
        }
    }



}
