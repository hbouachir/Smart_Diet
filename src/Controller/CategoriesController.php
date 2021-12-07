<?php

namespace App\Controller;

use App\Entity\Categories;
use App\Form\CategoriesType;

use App\Repository\categoriesRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategoriesController extends AbstractController
{
    /**
     * @Route("/categories", name="categories")
     */
    public function index(): Response
    {
        return $this->render('categories/index.html.twig', [
            'controller_name' => 'CategoriesController',
        ]);
    }

    /**
     * @param categoriesRepository $repo
     * @return Symfony\Component\HttpFoundation\Response
     * @Route("AfficheC/",name="C")
     */
    function Affiche(categoriesRepository $repo){


        $categories=$repo->findAll();

        return $this->render('categories/index1.html.twig',
            ['c'=>$categories]);
    }
    /**
     * @param Request $request
     * @return Response
     * @Route ("AjoutC/" ,name="Ajc")
     */
    function ajout(Request $request){
        $categories=new Categories();
        $form=$this->createForm(  CategoriesType::class,$categories);

        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $em->persist($categories);
            $em->flush();
            return $this->redirectToRoute('C');

        }
        return $this->render("categories/Ajout.html.twig",
            ['c'=>$form->createView()]);


    }


    /**
     * @Route ("Supprimer/{id}",name="DC")
     */
    function remove($id,categoriesRepository $repository,Request $request){
        $categories=$repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($categories);
        $em->flush();
        return $this->redirectToRoute('C');

    }


    /**
     * @Route ("modifier/{id}",name="m")
     */
    function Update(Request $request,categoriesRepository $repository,$id){
        $categories=$repository->find($id);
        $form=$this->createForm(CategoriesType::class,$categories);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
            return $this->redirectToRoute('C');

        }
        return $this->render("categories/Ajout.html.twig",
            ['c'=>$form->createView()]);

    }


}
