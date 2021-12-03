<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="idClient", columns={"idClient"})})
 * @ORM\Entity
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
     * @var int
     *
     * @ORM\Column(name="montantPanier", type="integer", nullable=false)
     */
    private $montantpanier;

    /**
     * @var \Personne
     *
     * @ORM\ManyToOne(targetEntity="Personne")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idClient", referencedColumnName="id")
     * })
     */
    private $idclient;

    public function getIdcommande(): ?int
    {
        return $this->idcommande;
    }

    public function getMontantpanier(): ?int
    {
        return $this->montantpanier;
    }

    public function setMontantpanier(int $montantpanier): self
    {
        $this->montantpanier = $montantpanier;

        return $this;
    }

    public function getIdclient(): ?Personne
    {
        return $this->idclient;
    }

    public function setIdclient(?Personne $idclient): self
    {
        $this->idclient = $idclient;

        return $this;
    }

    public function __toString() {
        return (string)($this->idclient);
    }


}
