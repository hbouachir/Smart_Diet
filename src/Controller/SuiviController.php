<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Entity\Suivi;
use App\Form\PersonneFormType;
use App\Form\SuiviFormType;
use App\Repository\SuiviRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class SuiviController extends AbstractController
{
    /**
     * @Route("/suivi", name="suivi")
     */
    public function index(SuiviRepository $suiviRepository): Response
    {
        return $this->render('security/nut.html.twig', [
             'c' => $suiviRepository->findAll()
        ]);
    }
    /**
     * @Route ("AFFS/",name="AS")
     */
    public function Afficher (SuiviRepository $repo){
        $suivi=$repo->findAll();
        return $this->render('suivi/Affiche.html.twig',
            ['c'=>$suivi]);
    }

    /**
     * @Route ("Del/{id}",name="DS")
     */
    function Delete ($id,SuiviRepository  $repository){
        $sui=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($sui);
        $em->flush();
      #  return $this->redirectToRoute('AS');
        return $this->render('security/nut.html.twig', [
              'c' => $repository->findAll()
        ]);
    }
    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajouts/" ,name="AjouS")
     */
    function ajout (Request $request,SuiviRepository $repository){
        $session = $request->getSession();
        if (!$session->has('id'))
        {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        }
        else
        {$suivi= new Suivi();
            $form = $this->createForm(SuiviFormType::class, $suivi);
            $form->add('Ajouter',SubmitType::class);

            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($suivi);
                $entityManager->flush();

               # return $this->redirectToRoute('AS');
                return $this->render('security/nut.html.twig', [
                    'c' => $repository->findAll()
                ]);
            }}
        return $this->render("suivi/Ajou.html.twig",
            ['f'=>$form->createView()]);


    }


    /**
     * @Route ("Up/{id}",name="US")
     */
    function Update(Request $request,SuiviRepository $repository,$id){
        $user=$repository->find($id);
        $form=$this->createForm(SuiviFormType::class,$user);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
           # return $this->redirectToRoute('AS');
            return $this->render('security/nut.html.twig', [
                'c' => $repository->findAll()
            ]);
        }
        return $this->render("suivi/Ajou.html.twig",
            ['f'=>$form->createView()]);

    }

}
