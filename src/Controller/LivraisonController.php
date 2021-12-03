<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Form\LivraisonType;
use App\Repository\LivraisonRepository;
use App\Repository\PersonneRepository;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Entity\Livraison;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Routing\Annotation\Route;

class LivraisonController extends AbstractController
{
    /**
     * @Route("/livraison", name="livraison")
     */
    public function index(LivraisonRepository $repository)

    {   $livraison=$repository->findAll();
        return $this->render('livraison/livraison.html.twig',
            ['livraison' => $livraison
        ]);
    }
    /**
     * @param LivraisonRepository $repository
     * @return Response
     * @Route("/Affichedash",name="Affichedash")
     */
    public function Affichedash(LivraisonRepository $repository,ReclamationRepository $repositoryR){
        //$repo=$this->getDoctrine()->getRepository(Classroom::class);
        $livraison=$repository->findAll();
        $reclamation=$repositoryR->findAll();
        return $this->render('livraison/intAdmin.html.twig',
            ['livraison'=>$livraison,'reclamation'=>$reclamation]);
    }
    /**
     * @Route("/Deldash/{id}",name="suppD")
     */
    function SupprimerD($id,LivraisonRepository $repository){
        $livraison=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($livraison);
        $em->flush();
        return $this->redirectToRoute('Affichedash');

    }
    /**
     * @param LivraisonRepository $repository
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("livraisondash/Update/{id}",name="updateD")
     */
    function ModifierD(LivraisonRepository $repository,$id,Request $request){
        $livraison = $repository->find($id);
        $form = $this->createForm(LivraisonType::class, $livraison);
        $form->add('Modifier', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Affichedash');

        }
        return $this->render('livraison/Update.html.twig',
            [
                'form' => $form->createView()
            ]);
    }





    /**
     * @param LivraisonRepository $repository
     * @return Response
     * @Route("/AfficheLivraison",name="AfficheLivraison")
     */
    public function Affiche(LivraisonRepository $repository){
        //$repo=$this->getDoctrine()->getRepository(Classroom::class);
        $livraison=$repository->findAll();
        return $this->render('livraison/intAdmin.html.twig',
            ['livraison'=>$livraison]);
    }

    /**
     * @Route("/Del/{id}",name="supp")
     */
    function Supprimer($id,LivraisonRepository $repository){
        $livraison=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($livraison);
        $em->flush();
        return $this->redirectToRoute('livraison');

    }


    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("livraison/Add",name="add")
     */
    function Ajouter(Request $request,PersonneRepository $personneRepository){
        $livraison=new Livraison();
        $form=$this->createForm(LivraisonType::class,$livraison);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted()&&$form->isValid()){
            $addresse=$livraison->getIdcommande()->getIdclient()->getAdresse();
            $livreurs=$personneRepository->findBy([
                'zone'=>$addresse
            ]);
            $MIN=0;
            for ($i=0;$i<count($livreurs); ++$i){
                if($livreurs[$i]->getNombrelivraison()<$livreurs[$MIN]->getNombrelivraison()){
                    //$livreur=$livreurs[$MIN];
                    $MIN=$i;
                }
            }

            $livreurs[$MIN]->setNombrelivraison($livreurs[$MIN]->getNombrelivraison()+1);
            $livraison->setIdlivreur($livreurs[$MIN]);
            $em=$this->getDoctrine()->getManager();
            $em->persist($livraison);
            $em->flush();
            return $this->redirectToRoute('Affichedash');
        }
        return $this->render('livraison/Add.html.twig',[
            'form'=>$form->createView()
        ]);
    }

    /**
     * @param PersonneRepository $personneRepository
     * @return Response
     * @Route("livraison/Meilleur",name="meilleur")
     */
    function MeilleurLivreur(PersonneRepository $personneRepository,ReclamationRepository $reclamationR){
        $livreurs=$personneRepository->findAll();
        $MAX=0;
        for ($i=0;$i<count($livreurs); ++$i){
            if($livreurs[$i]->getNombrelivraison()>$livreurs[$MAX]->getNombrelivraison()){
                //$livreur=$livreurs[$MIN];
                $MAX=$i;
            }
        }
        return $this->render('livraison/meilleurLivreur.html.twig',
            ['livreur'=>$livreurs[$MAX],'reclamation'=>$reclamationR]);

    }


    /**
     * @param LivraisonRepository $livraisonRepository
     * @return Response
     * @Route("livraison/Livree",name="recherche1")
     */
    function RechercherLivraison1(LivraisonRepository $livraisonRepository,ReclamationRepository $reclamationR){
        $livraisons=$livraisonRepository->findBy([
            'etatlivraison'=>'1'
        ]);

        return $this->render('livraison/intAdmin.html.twig',
            ['livraison'=>$livraisons,'reclamation'=>$reclamationR]);
    }

    /**
     * @param LivraisonRepository $livraisonRepository
     * @return Response
     * @Route("livraison/nonLivree",name="recherche0")
     */
    function RechercherLivraison0(LivraisonRepository $livraisonRepository,ReclamationRepository $reclamationR){
        $livraisons=$livraisonRepository->findBy([
            'etatlivraison'=>'0'
        ]);

        return $this->render('livraison/intAdmin.html.twig',
            ['livraison'=>$livraisons,'reclamation'=>$reclamationR]);
    }

    /**
     * @param LivraisonRepository $repository
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     * @Route("livraison/Update/{id}",name="update")
     */
    function Modifier(LivraisonRepository $repository,$id,Request $request){
        $livraison = $repository->find($id);
        $form = $this->createForm(LivraisonType::class, $livraison);
        $form->add('Modifier', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheLivraison');

        }
        return $this->render('livraison/Update.html.twig',
            [
                'form' => $form->createView()
            ]);
    }
}
