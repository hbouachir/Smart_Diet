<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="userid", columns={"idClient"})})
 * @ORM\Entity(repositoryClass="App\Repository\CommandeRepository")
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="idCommande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcommande;

    /**
     * @var float
     *
     * @ORM\Column(name="montantPanier", type="float", precision=10, scale=0, nullable=false)
     */
    private $montantpanier;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=256, nullable=false, options={"default"="en cours"})
     */
    private $etat = 'en cours';

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idClient", referencedColumnName="id")
     * })
     */
    private $idclient;

    public function getIdcommande(): ?int
    {
        return $this->idcommande;
    }

    public function getMontantpanier(): ?float
    {
        return $this->montantpanier;
    }

    public function setMontantpanier(float $montantpanier): self
    {
        $this->montantpanier = $montantpanier;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getIdclient(): ?User
    {
        return $this->idclient;
    }

    public function setIdclient(?User $idclient): self
    {
        $this->idclient = $idclient;

        return $this;
    }


}
