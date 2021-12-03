<?php

namespace App\Controller;

use App\Entity\UserApp;
use App\Form\UserAppType;
use App\Repository\UserAppRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/user/app")
 */
class UserAppController extends AbstractController
{
    /**
     * @Route("/", name="user_app_index", methods={"GET"})
     */
    public function index(UserAppRepository $userAppRepository): Response
    {
        return $this->render('user_app/index.html.twig', [
            'user_apps' => $userAppRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="user_app_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $userApp = new UserApp();
        $form = $this->createForm(UserAppType::class, $userApp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($userApp);
            $entityManager->flush();

            return $this->redirectToRoute('user_app_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user_app/new.html.twig', [
            'user_app' => $userApp,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="user_app_show", methods={"GET"})
     */
    public function show(UserApp $userApp): Response
    {
        return $this->render('user_app/show.html.twig', [
            'user_app' => $userApp,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="user_app_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, UserApp $userApp): Response
    {
        $form = $this->createForm(UserAppType::class, $userApp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('user_app_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user_app/edit.html.twig', [
            'user_app' => $userApp,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="user_app_delete", methods={"POST"})
     */
    public function delete(Request $request, UserApp $userApp): Response
    {
        if ($this->isCsrfTokenValid('delete'.$userApp->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($userApp);
            $entityManager->flush();
        }

        return $this->redirectToRoute('user_app_index', [], Response::HTTP_SEE_OTHER);
    }
}
