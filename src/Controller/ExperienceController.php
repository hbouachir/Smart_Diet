<?php

namespace App\Controller;

use App\Entity\Experience;
use App\Form\ExperienceType;
use App\Repository\ExperienceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ExperienceController extends AbstractController
{
    /**
     * @Route("/experience", name="experience")
     */
    public function index(): Response
    {
        return $this->render('experience/index.html.twig', [
            'controller_name' => 'ExperienceController',
        ]);
    }


    /**
     * @param ExperienceRepository $repo
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/experience/affiche", name="experienceAffiche")
     */
    public function exprerienceAffiche(ExperienceRepository $repo){
        $experiences=$repo->findAll();
        return $this->render('experience/affiche.html.twig',['experiences'=>$experiences]);
    }



    /**
     * @Route("/experience/supprimer/{id}", name="experienceSupprime")
     */
    public function exprerienceSupprime($id,ExperienceRepository $repo){
        $experience=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($experience);
        $em->flush();
        return $this->redirectToRoute(
            'experienceAffiche');
    }


    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/experience/ajout", name="experienceAjoute")
     */
    public function exprerienceAjoute(Request $request){
        $experience = new Experience();
        $form=$this->createForm(ExperienceType::class,$experience);
        $form->handleRequest($request);
       // $form->add('Ajoute',SubmitType::class);
        if ( $form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($experience);
            $em->flush();


            return $this->redirectToRoute('experienceAffiche');



        }



        return $this->render(
            'experience/ajout.html.twig',['form'=>$form->createView()]);
    }




    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     * @Route("/experience/modifie/{id}", name="experienceModifie")
     */
    public function exprerienceModifie($id,Request $req,ExperienceRepository $rep){
        $experience=$rep->find($id);
        $form=$this->createForm(ExperienceType::class,$experience);

        $form->handleRequest($req);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('experienceAffiche');

        }

        return $this->render(
            'experience/modifie.html.twig',['form'=>$form->createView()]);
    }



}
