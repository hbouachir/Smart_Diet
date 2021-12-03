<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Consultation
 *
 * @ORM\Table(name="consultation", indexes={@ORM\Index(name="idRdv", columns={"idRdv"})})
 * @ORM\Entity
 */
class Consultation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idConsultation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idconsultation;

    /**
     * @var string
     *
     * @ORM\Column(name="ordonnance", type="string", length=255, nullable=false)
     */
    private $ordonnance;

    /**
     * @var \Rendezvous
     *
     * @ORM\ManyToOne(targetEntity="Rendezvous")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idRdv", referencedColumnName="idRdv")
     * })
     */
    private $idrdv;

    public function getIdconsultation(): ?int
    {
        return $this->idconsultation;
    }

    public function getOrdonnance(): ?string
    {
        return $this->ordonnance;
    }

    public function setOrdonnance(string $ordonnance): self
    {
        $this->ordonnance = $ordonnance;

        return $this;
    }

    public function getIdrdv(): ?Rendezvous
    {
        return $this->idrdv;
    }

    public function setIdrdv(?Rendezvous $idrdv): self
    {
        $this->idrdv = $idrdv;

        return $this;
    }


}
