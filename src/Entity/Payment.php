<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Payment
 *
 * @ORM\Table(name="payment", indexes={@ORM\Index(name="idFacture", columns={"idFacture"})})
 * @ORM\Entity
 */
class Payment
{
    /**
     * @var int
     *
     * @ORM\Column(name="idPayment", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idpayment;

    /**
     * @var int
     *
     * @ORM\Column(name="numeroCompte", type="integer", nullable=false)
     */
    private $numerocompte;

    /**
     * @var int
     *
     * @ORM\Column(name="password", type="integer", nullable=false)
     */
    private $password;

    /**
     * @var \Facture
     *
     * @ORM\ManyToOne(targetEntity="Facture")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idFacture", referencedColumnName="idFacture")
     * })
     */
    private $idfacture;

    public function getIdpayment(): ?int
    {
        return $this->idpayment;
    }

    public function getNumerocompte(): ?int
    {
        return $this->numerocompte;
    }

    public function setNumerocompte(int $numerocompte): self
    {
        $this->numerocompte = $numerocompte;

        return $this;
    }

    public function getPassword(): ?int
    {
        return $this->password;
    }

    public function setPassword(int $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getIdfacture(): ?Facture
    {
        return $this->idfacture;
    }

    public function setIdfacture(?Facture $idfacture): self
    {
        $this->idfacture = $idfacture;

        return $this;
    }


}
