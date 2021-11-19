<?php

namespace App\Controller;

use App\Entity\Lignecommande;
use App\Form\LignecommandeType;
use App\Repository\LigneCommandeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/lignecommande")
 */
class LignecommandeController extends AbstractController
{
    /**
     * @Route("/", name="lignecommande_index", methods={"GET"})
     */
    public function index(LigneCommandeRepository $ligneCommandeRepository): Response
    {
        return $this->render('lignecommande/index.html.twig', [
            'lignecommandes' => $ligneCommandeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="lignecommande_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $lignecommande = new Lignecommande();
        $form = $this->createForm(LignecommandeType::class, $lignecommande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($lignecommande);
            $entityManager->flush();

            return $this->redirectToRoute('lignecommande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('lignecommande/new.html.twig', [
            'lignecommande' => $lignecommande,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idligne}", name="lignecommande_show", methods={"GET"})
     */
    public function show(Lignecommande $lignecommande): Response
    {
        return $this->render('lignecommande/show.html.twig', [
            'lignecommande' => $lignecommande,
        ]);
    }

    /**
     * @Route("/{idligne}/edit", name="lignecommande_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Lignecommande $lignecommande): Response
    {
        $form = $this->createForm(LignecommandeType::class, $lignecommande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('lignecommande_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('lignecommande/edit.html.twig', [
            'lignecommande' => $lignecommande,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idligne}", name="lignecommande_delete", methods={"POST"})
     */
    public function delete(Request $request, Lignecommande $lignecommande): Response
    {
        if ($this->isCsrfTokenValid('delete'.$lignecommande->getIdligne(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($lignecommande);
            $entityManager->flush();
        }

        return $this->redirectToRoute('lignecommande_index', [], Response::HTTP_SEE_OTHER);
    }
}
