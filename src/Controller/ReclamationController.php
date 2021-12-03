<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Repository\ReclamationRepository;
use App\Form\ReclamationType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class ReclamationController extends AbstractController
{
    /**
     * @Route("/reclamation", name="reclamation")
     */
    public function index(): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    }

    /**
     * @param ReclamationRepository $repository
     * @return Response
     * @Route("/AfficherReclamation",name="AfficheReclamation")
     */
    public function Affiche(ReclamationRepository $repository){
        //$repo=$this->getDoctrine()->getRepository(Classroom::class);
        $reclamation=$repository->findAll();
        return $this->render('livraison/AfficheReclamation.html.twig',
            ['reclamation'=>$reclamation]);
    }

    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("reclamation/Add",name="addrec")
     */
    function Ajouter(Request $request){
        $reclamation=new Reclamation();
        $form=$this->createForm(ReclamationType::class,$reclamation);
        //$form->add('Envoyer',SubmitType::class);

            $form->handleRequest($request);
            if($form->isSubmitted()&&$form->isValid()){
                $em=$this->getDoctrine()->getManager();
                $em->persist($reclamation);
                $em->flush();
                return $this->redirectToRoute('AfficheReclamation');

        }






        return $this->render('livraison/reclamation.html.twig',[
            'form'=>$form->createView()]);
    }

    /**
     * @param $id
     * @param ReclamationRepository $repository
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     * @Route("DelR/{id}",name="supprec")
     */
    function Supprimer($id,ReclamationRepository $repository){
        $reclamation=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('AfficheReclamation');

    }

    /**
     * @param $id
     * @param ReclamationRepository $repository
     * @return Response
     * @Route("ShowR/{id}",name="showrec")
     */
    function ShowReclamation($id,ReclamationRepository $repository){
        $reclamation=$repository->find($id);

        return $this->render("livraison/ShowRec.html.twig",
            ['rec'=>$reclamation]);

    }

    /**
     * @param $search
     * @param ReclamationRepository $repository
     * @return Response
     * @Route("Search/{message}",name="searchrec")
     */
    function SearchMessage($search,ReclamationRepository $repository){
        $reclamations=$repository->findAll();
        for ($i=0;$i<count($reclamations); ++$i){
            if(strpos($reclamations[$i]->getDescription(),$search)!= false){
                $reclamation=$reclamation[$i];
            }
        }


        return $this->render("livraison/ShowRec.html.twig",
            ['rec'=>$reclamation]);

    }


    /**
     * @param Request $request
     * @param ReclamationRepository $repository
     * @param $id
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("Update/{id}",name="uprec")
     */
    function Update(Request $request,ReclamationRepository $repository,$id){
        $reclamation=$repository->find($id);
        $form=$this->createForm(ReclamationType::class,$reclamation);
        //$form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() &&$form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheReclamation');

        }
        return $this->render("livraison/reclamation.html.twig",
            ['form'=>$form->createView()]);

    }


}
