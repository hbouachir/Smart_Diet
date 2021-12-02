<?php

namespace App\Controller;
use Knp\Component\Pager\PaginatorInterface;
use App\Entity\Login;
use App\Entity\PriceSearch;
use App\Entity\Produit;
use App\Entity\PropertySearch;
use App\Form\LoginType;
use App\Form\PriceSearchType;
use App\Form\ProduitType;
use App\Form\PropertySearchType;
use App\Repository\produitRepository;
use Doctrine\ORM\Tools\Pagination\Paginator;
use MercurySeries\Flashy\FlashyNotifier;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use  Symfony\Component\Form\pproduit;
use Symfony\Component\HttpFoundation\Request;
use mysql_xdevapi\Statement;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Message;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Notifier\Message\SmsMessage;
use Symfony\Component\Notifier\TexterInterface;



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
     * @Route("List/",name="L")
     */
    function List(produitRepository $repo,Request $request,PaginatorInterface $paginator){
        //request select * from produit
        //$rep=$this->getDoctrine()->getRepository(produit::class);
        $produit=$repo->findAll();
        $em = $this->getDoctrine()->getManager();
        $produit= $em ->getRepository(produit::class)->findAll();
        $produit=$paginator->paginate(
            $produit, //on passe les données
            $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
            6 //nbre de produits par page
        );

        if ($request->isMethod("POST"))
        {
            $categorie = $request->get('categorie');
            $produit = $em->getRepository(Produit::class)->findBy(array('categorie'=>$categorie));
            $produit=$paginator->paginate(
                $produit, //on passe les données
                $request->query->getInt('page', 1), //num de la page en cours, 1 par défaut
                6 //nbre de produits par page
            );

        }


        return $this->render('produit/liste.html.twig',
            ['p'=>$produit]);
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
    /**
     * @Route("/triid", name="triid")
     */
     public function Triid(Request $request)
     {
         $em = $this->getDoctrine()->getManager();

         $query = $em->createQuery(
             'SELECT a FROM App\Entity\Produit a 
             ORDER BY a.quantite ASC'
         );

         $rep = $query->getResult();
         $this->addFlash(
             'info','list of the most ordered products'
         );

         return $this->render('produit/index.html.twig',
             array('p' => $rep));

     }


    /**
     * @Route("/pdfprod", name="pdfprod")
     */

    public function ImprimerCommande(produitRepository $repository):Response
    {

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);



        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('produit/AfficheProduit.html.twig',
            ['p'=>$repository->findAll()]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4','landscape');


        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Produit_finale.pdf", [
            "Attachment" => true
        ]);
        return $this->redirectToRoute('A');

    }

    /**
     * @Route("/stat", name="/stat")
     */
    public function indexAction(){
        $repository = $this->getDoctrine()->getRepository(Produit::class);
        $produit = $repository->findAll();
        $em = $this->getDoctrine()->getManager();

        $c1=0;
        $c2=0;
        $c3=0;
        $c4=0;



        foreach ($produit as $produit)
        {
            if (  $produit->getCategorie()=="A")  :

                $c1+=1;
            elseif( $produit->getCategorie()=="B"):
                $c2+=1;
            ;
            elseif ($produit->getCategorie()=="C"):

                $c3+=1;
            else :
                $c4 +=1;

            endif;

        }


        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['catégories', 'nombres'],
                ['A',     $c1],
                ['B',      $c2],
                ['C',   $c3],
                ['D',   $c4]]

        );
        $pieChart->getOptions()->setTitle('Top categories');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('produit/stat.html.twig', array('piechart' => $pieChart));
    }



    /**
     * @Route("/art_prix/", name="produits_par_prix")
     * Method({"GET"})
     */
    public function produitParPrix(Request $request,produitRepository $repository)
    {

        $priceSearch = new PriceSearch();
        $form = $this->createForm(PriceSearchType::class,$priceSearch);
        $form->handleRequest($request);

        $produits = $repository->findAll();

        if($form->isSubmitted() && $form->isValid()) {
            $minPrice = $priceSearch->getMinPrice();
            $maxPrice = $priceSearch->getMaxPrice();

            $produits= $this->getDoctrine()->getRepository(Produit::class)->findByPriceRange($minPrice,$maxPrice);
        }

        return  $this->render('produit/prix.html.twig',[ 'p' =>$form->createView(), 'produits' => $produits]);
    }

    /**
     * @Route("/login", name="login")
     * Method({"GET"})
     */

    public function LoginSession(Request $request){

        $login = new Login();
        $form = $this->createForm(LoginType::class,$login);
        $form->handleRequest($request);
        $form->isSubmitted() && $form->isValid();
          if ($login->getNom()=="moez" &&$login->getMdp()=="moez")
        {
            return $this->redirectToRoute('A');
        }
       return $this->render('produit/login.html.twig',[ 'p' =>$form->createView(), 'login' => $login]);
    }




}


