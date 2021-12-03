<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\LigneCommande;
use App\Repository\AgenceRepository;
use App\Repository\CommandeRepository;
use App\Repository\ImageProduitRepository;
use App\Repository\ProduitRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

class PanierController extends AbstractController
{

    /**
     * @Route("/panier/add/{id}", name="cart_add")
     */
    public function add($id, SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get('panier', []);
        if (!empty($panier[$id])) {
            $panier[$id]++;
        } else {
            $panier[$id] = 1;
        }
        $session->set('panier', $panier);
        $panierwithdata = [];
        foreach ($panier as $id => $quantite) {
            $panierwithdata[] = [
                'produit' => $produitRepository->find($id),
                'quantite' => $quantite
            ];
        }
        $total = 0;
        $totalqte = 0;
        foreach ($panierwithdata as $produit) {
            $totalproduit = $produit['produit']->getPrix() * $produit['quantite'];
            $total += $totalproduit;
            $totalqte += $produit['quantite'];
        }
        $session->set('panierwithdata', $panierwithdata);
        $session->set('totalqte', $totalqte);
        $session->set('total', $total);
        return $this->redirectToRoute('produit_index');
    }

    /**
     * @Route("/panier/ajout/{id}", name="cart_ajout")
     */
    public function ajout($id, SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get('panier', []);
        if (!empty($panier[$id])) {
            $panier[$id]++;
        } else {
            $panier[$id] = 1;
        }
        $session->set('panier', $panier);
        $panierwithdata = [];
        foreach ($panier as $id => $quantite) {
            $panierwithdata[] = [
                'produit' => $produitRepository->find($id),
                'quantite' => $quantite
            ];
        }
        $total = 0;
        $totalqte = 0;
        foreach ($panierwithdata as $produit) {
            $totalproduit = $produit['produit']->getPrix() * $produit['quantite'];
            $total += $totalproduit;
            $totalqte += $produit['quantite'];
        }
        $session->set('panierwithdata', $panierwithdata);
        $session->set('totalqte', $totalqte);
        $session->set('total', $total);
        return $this->redirectToRoute('cart_index');
    }

    /**
     * @Route("/panier/min/{id}", name="cart_minus")
     */
    public function min($id, SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get('panier', []);
        if (!empty($panier[$id])) {
            $panier[$id]--;
        } else {
            $panier[$id] = 1;
        }
        $session->set('panier', $panier);
        $panierwithdata = [];
        foreach ($panier as $id => $quantite) {
            $panierwithdata[] = [
                'produit' => $produitRepository->find($id),
                'quantite' => $quantite-1
            ];
        }
        $total = 0;
        $totalqte = 0;
        foreach ($panierwithdata as $produit) {
            $totalproduit = $produit['produit']->getPrix() * $produit['quantite'];
            $total += $totalproduit;
            $totalqte += $produit['quantite'];
        }
        $session->set('panierwithdata', $panierwithdata);
        $session->set('totalqte', $totalqte);
        $session->set('total', $total);
        return $this->redirectToRoute('cart_index');
    }

    /**
     * @Route("/panier", name="cart_index")
     */
    public function index(CommandeRepository $commandeRepository, SessionInterface $session, ProduitRepository $produitRepository)
    {

        $session = $commandeRepository->produitPanier($session, $produitRepository);

        return $this->render('boutiqueEnLigne/panier.html.twig', [
            'produits' => $session->get('panierwithdata'),
            'total' => $session->get('total'),

        ]);
    }

    /**
     * @Route("/panier/supprimer/{id}", name="cart_supp")
     */
    public function remove($id, SessionInterface $session)
    {
        $panier = $session->get('panier', []);
        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }
        $session->set('panier', $panier);
        return $this->redirectToRoute('cart_index');
    }


    /**
     * @Route("/commande", name="commander")
     */
    public function commande(CommandeRepository $commandeRepository, SessionInterface $session, ProduitRepository $produitRepository)
    {

        if (isset($_POST['commander'])) {
            $this->denyAccessUnlessGranted('IS_AUTHENTICATED_FULLY');
            $session = $commandeRepository->produitPanier($session, $produitRepository);
            return $this->render('commande/livraison.html.twig', [
                'produits' => $session->get('panierwithdata'),
                'total' => $session->get('total'),
            ]);
        }
    }

    /**
     * @Route("/commandeConfirmer", name="commandeConfirmer")
     */
    public function commandeConfirmer(CommandeRepository $commandeRepository, MailerInterface $mailer, SessionInterface $session, ProduitRepository $produitRepository)
    {
        $user = $this->getUser();

        $session = $commandeRepository->produitPanier($session, $produitRepository);
        $commande = new Commande();
        $commande->setDate(new \DateTime());
        $commande->setPrixTotal($session->get('total'));
        $commande->setQuantite($session->get('totalqte'));
        $commande->setUserApp($user);

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($commande);
        $entityManager->flush();
        foreach ($session->get('panierwithdata') as $prod) {
            $ligneCommande = new LigneCommande();

            $ligneCommande->setQuantite($prod['quantite']);

            $ligneCommande->setProduit($prod['produit']);
            $ligneCommande->setCommande($commande);
            $entityManager = $this->getDoctrine()->getManager();
            if ($ligneCommande->getQuantite()>$ligneCommande->getProduit()->getQteStock()){
                echo "<script>alert(\"produit epuisé\")
</script>";
            }else{
            $entityManager->persist($ligneCommande);
            $entityManager->flush();}
            $email = (new Email())
                ->from('test.esprit2021@gmail.com')
                ->to($this->getUser()->getUsername())
                //->cc('cc@example.com')
                //->bcc('bcc@example.com')
                //->replyTo('fabien@example.com')
                //->priority(Email::PRIORITY_HIGH)
                ->subject('Commande !')
                ->text('Sending emails is fun again!')
                ->html('<p>votre commande est effectué avec succès je vous prie de passer au paiement   Merci!</p>');

            $mailer->send($email);

        $session->set('panier', null);
        $session->set('panierwithdata', null);
        $session->set('totalqte', null);
        $session->set('total', null);}



        return $this->redirectToRoute('commande_index', [], Response::HTTP_SEE_OTHER);


    }



}
