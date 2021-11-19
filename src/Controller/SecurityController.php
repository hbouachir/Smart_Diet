<?php
// src/Controller/SecurityController.php
namespace App\Controller;

use App\Repository\PersonneRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

class SecurityController extends AbstractController
{
    /**
     * @Route("/login1", name="login1")
     */
    public function loginAction (Request $request, PersonneRepository  $userRepository)
    {

        if ($request->isMethod('POST')) {
            // load the user in some way (e.g. using the form input)
            $email = $request->request->get('mail');

            $user = $userRepository->findOneBy(['mail' => $email]);
            $em = $this->getDoctrine()->getManager();
            return $this->render('security/login.html.twig', array(
                'last_username' => $email,

            ));
        }
    }
}