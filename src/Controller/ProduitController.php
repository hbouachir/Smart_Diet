<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\produitRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use  Symfony\Component\Form\pproduit;
use Symfony\Component\HttpFoundation\Request;
use mysql_xdevapi\Statement;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProduitController extends AbstractController
{
    /**
     * @Route("/produit", name="produit")
     */
    public function index(): Response
    {
        return $this->render('produit/index.html.twig', [
            'controller_name' => 'ProduitController',
        ]);
    }


    /**
     * @param produitRepository $repo
     * @return Symfony\Component\HttpFoundation\Response
     * @Route("Affiche/",name="A")
     */
    function Affiche(produitRepository $repo){
        //request select * from produit
        //$rep=$this->getDoctrine()->getRepository(produit::class);
        $produit=$repo->findAll();

        return $this->render('produit/index.html.twig',
            ['p'=>$produit]);
    }

    /**
     * @Route ("Delete/{id}",name="D")
     */
    function remove($id,produitRepository $repository,Request $request){
        $produit=$repository->find($id);
        $form=$this->createForm(ProduitType::class,$produit);
        $form->add('Delete',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($produit);
            $em->flush();
            return $this->redirectToRoute('A');
        }

        return $this->render("produit/Ajout.html.twig",
            ['p'=>$form->createView()]);

    }
    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajout/")
     */
    function ajout(Request $request){
        $produit=new Produit();
        $form=$this->createForm(ProduitType::class,$produit);
        $form->add('Ajouter Produit',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();
            return $this->redirectToRoute('A');

        }
        return $this->render("produit/Ajout.html.twig",
            ['p'=>$form->createView()]);


    }

    /**
     * @Route ("Update/{id}",name="U")
     */
    function Update(Request $request,produitRepository $repository,$id){
        $produit=$repository->find($id);
        $form=$this->createForm(ProduitType::class,$produit);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
            return $this->redirectToRoute('A');

        }
        return $this->render("produit/Ajout.html.twig",
            ['p'=>$form->createView()]);

    }





}
