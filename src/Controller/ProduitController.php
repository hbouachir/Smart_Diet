<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\produitRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/produit")
 */
class ProduitController extends AbstractController
{



    /**
     * @param produitRepository $repo
     * @return Symfony\Component\HttpFoundation\Response
     * @Route("List/",name="L")
     */
    function List(produitRepository $repo,Request $request){
        //request select * from produit
        //$rep=$this->getDoctrine()->getRepository(produit::class);
        $produit=$repo->findAll();
        $em = $this->getDoctrine()->getManager();
        $produit= $em ->getRepository(produit::class)->findAll();


        if ($request->isMethod("POST"))
        {
            $categorie = $request->get('categorie');
            $produit = $em->getRepository(Produit::class)->findBy(array('categorie'=>$categorie));


        }


        return $this->render('produit/liste.html.twig',
            ['p'=>$produit]);
    }





    /**
     * @param produitRepository $repo
     * @return Symfony\Component\HttpFoundation\Response
     * @Route("AfficheProduit/",name="A")
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

        $em = $this->getDoctrine()->getManager();
        $em->remove($produit);
        $em->flush();
        $this->addFlash(
            'info','product deleted successfuly'
        );

        return $this->redirectToRoute('A');

    }


    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajout/",name="Aj")
     */
    function ajout(Request $request){
        $produit=new Produit();
        $form=$this->createForm(ProduitType::class,$produit);

        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();
            $this->addFlash(
                'info','product added successfuly'
            );
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
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            //$em->persist($classroom);
            $em->flush();
            $this->addFlash(
                'info','List of products update successfuly'
            );
            if ($produit->getQuantite()==0){
                $em = $this->getDoctrine()->getManager();
                $em->remove($produit);
                $em->flush();


            }
            if ($produit->getQuantite()<=5){
                $this->addFlash(
                    'info','check stock'
                );
            }
            return $this->redirectToRoute('A');

        }


        return $this->render("produit/Ajout.html.twig",
            ['p'=>$form->createView()]);

    }






}
