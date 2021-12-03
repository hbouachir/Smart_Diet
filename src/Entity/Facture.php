<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Facture
 *
 * @ORM\Table(name="facture", indexes={@ORM\Index(name="idCommande", columns={"idCommande"})})
 * @ORM\Entity
 */
class Facture
{
    /**
     * @var int
     *
     * @ORM\Column(name="idFacture", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idfacture;

    /**
     * @var int
     *
     * @ORM\Column(name="etatPayment", type="integer", nullable=false)
     */
    private $etatpayment;

    /**
     * @var string
     *
     * @ORM\Column(name="dateFacture", type="string", length=255, nullable=false)
     */
    private $datefacture;

    /**
     * @var \Commande
     *
     * @ORM\ManyToOne(targetEntity="Commande")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idCommande", referencedColumnName="idCommande")
     * })
     */
    private $idcommande;

    public function getIdfacture(): ?int
    {
        return $this->idfacture;
    }

    public function getEtatpayment(): ?int
    {
        return $this->etatpayment;
    }

    public function setEtatpayment(int $etatpayment): self
    {
        $this->etatpayment = $etatpayment;

        return $this;
    }

    public function getDatefacture(): ?string
    {
        return $this->datefacture;
    }

    public function setDatefacture(string $datefacture): self
    {
        $this->datefacture = $datefacture;

        return $this;
    }

    public function getIdcommande(): ?Commande
    {
        return $this->idcommande;
    }

    public function setIdcommande(?Commande $idcommande): self
    {
        $this->idcommande = $idcommande;

        return $this;
    }


}
