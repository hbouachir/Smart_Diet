<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Entity\Suivi;
use App\Form\PersonneFormType;
use App\Form\SuiviFormType;
use App\Repository\PersonneRepository;
use App\Repository\SuiviRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\Session;
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
     * @Route("/suiviC", name="suiviC")
     */
    public function indexC(Request $request,PersonneRepository $userRepository)
    {$session = $request->getSession();
        if (!$session->has('id'))
        {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        }
        else
        { $idd = $this->get('session')->get('id');
        return $this->render('security/client.html.twig', ['u'=>$userRepository->find($idd)

        ]);
    }}

    /**
     * @Route ("AFFS/",name="AS")
     */
    public function Afficher(SuiviRepository $repo)
    {
        $suivi = $repo->findAll();
        return $this->render('suivi/Affiche.html.twig',
            ['c' => $suivi]);
    }

    /**
     * @Route ("Del/{id}",name="DS")
     */
    function Delete($id, SuiviRepository $repository,Request $request)
    {$session = $request->getSession();
        $sui = $repository->find($id);
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {

        $em = $this->getDoctrine()->getManager();
        $em->remove($sui);
        $em->flush();
            $suivi = new Suivi();
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $suivi->setIduser($user);
            $suivi = $repository->findBy(['iduser'=>$suivi->getIduser()]);
        #  return $this->redirectToRoute('AS');
        return $this->render('suivi/listesuiviparclient.html.twig',
            ['c' => $suivi]);
    }}

    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajouts/" ,name="AjouS")
     */
    function ajout(Request $request, SuiviRepository $repository)
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $suivi = new Suivi();
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $suivi->setIduser($user);

       #     $d=strtotime("today");
            $now = (new \DateTime('now'))->format('Y-m-d H:i:s');
           # $suivi->setDatesuivi((string)new \DateTime());
            $suivi->setDatesuivi((string)$now);
            $form = $this->createForm(SuiviFormType::class, $suivi);
            $form->add('Ajouter', SubmitType::class);

            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {

                $entityManager = $this->getDoctrine()->getManager();

                $entityManager->persist($suivi);
                $entityManager->flush();
                $suivi = $repository->findBy(['iduser'=>$suivi->getIduser()]);
                return $this->render('suivi/listesuiviparclient.html.twig',
                    ['c' => $suivi]);

            }
        }
        return $this->render("suivi/Ajou.html.twig",
            ['f' => $form->createView()]);


    }


    /**
     * @Route ("Up/{id}",name="US")
     */
    function Update(Request $request, SuiviRepository $repository, $id)
    {$session = $request->getSession();
        $user = $repository->find($id);
        $form = $this->createForm(SuiviFormType::class, $user);
        $form->add('Update', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            if (!$session->has('id')) {
                $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
                return $this->redirectToRoute('user_login');
            } else {
                $suivi = new Suivi();
                $idd = $this->get('session')->get('id');
                $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
                $suivi->setIduser($user);
                $suivi = $repository->findBy(['iduser'=>$suivi->getIduser()]);
            $em = $this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
            # return $this->redirectToRoute('AS');
            return $this->render('suivi/listesuiviparclient.html.twig',
                ['c' => $suivi]);
        }}
        return $this->render("suivi/Ajou.html.twig",
            ['f' => $form->createView()]);

    }

    /**
     * @Route ("AFFSC/",name="ASC")
     */
    public function AfficherSC(SuiviRepository $repo, Request $request)
    {
        $session = $request->getSession();
        if (!$session->has('id')) {
            $this->get('session')->getFlashBag()->add('info', 'Erreur de  Connection veuillez se connecter .... ....');
            return $this->redirectToRoute('user_login');
        } else {
            $suivi = new Suivi();
            $idd = $this->get('session')->get('id');
            $user = $this->getDoctrine()->getRepository(Personne::class)->find($idd);
            $suivi->setIduser($user);
            $suivi = $repo->findBy(['iduser'=>$suivi->getIduser()]);
            return $this->render('suivi/listesuiviparclient.html.twig',
                ['c' => $suivi]);
        }
    }
}