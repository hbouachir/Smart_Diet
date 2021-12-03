<?php

namespace App\Repository;

use App\Entity\Commande;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

/**
 * @method Commande|null find($id, $lockMode = null, $lockVersion = null)
 * @method Commande|null findOneBy(array $criteria, array $orderBy = null)
 * @method Commande[]    findAll()
 * @method Commande[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CommandeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Commande::class);
    }

    public function produitPanier(SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get('panier', []);
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
        return $session;
    }
    // /**
    //  * @return Commande[] Returns an array of Commande objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Commande
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function clientFidele()
    {


        $query = $this->_em->createNativeQuery('SELECT email,SUM(prix_total) AS depenses
FROM commande INNER JOIN user_app ON commande.user_app_id=user_app.id 
GROUP BY user_app_id ORDER BY SUM(prix_total) DESC');


        $users = $query->getResult();
        return $users;

    }

}
