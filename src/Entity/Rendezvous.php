<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rendezvous
 *
 * @ORM\Table(name="rendezvous", indexes={@ORM\Index(name="idPatient", columns={"idPatient"})})
 * @ORM\Entity
 */
class Rendezvous
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRdv", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrdv;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateRdv", type="date", nullable=false)
     */
    private $daterdv;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heure", type="time", nullable=false)
     */
    private $heure;

    /**
     * @var \Personne
     *
     * @ORM\ManyToOne(targetEntity="Personne")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idPatient", referencedColumnName="id")
     * })
     */
    private $idpatient;

    public function getIdrdv(): ?int
    {
        return $this->idrdv;
    }

    public function getDaterdv(): ?\DateTimeInterface
    {
        return $this->daterdv;
    }

    public function setDaterdv(\DateTimeInterface $daterdv): self
    {
        $this->daterdv = $daterdv;

        return $this;
    }

    public function getHeure(): ?\DateTimeInterface
    {
        return $this->heure;
    }

    public function setHeure(\DateTimeInterface $heure): self
    {
        $this->heure = $heure;

        return $this;
    }

    public function getIdpatient(): ?Personne
    {
        return $this->idpatient;
    }

    public function setIdpatient(?Personne $idpatient): self
    {
        $this->idpatient = $idpatient;

        return $this;
    }


}
