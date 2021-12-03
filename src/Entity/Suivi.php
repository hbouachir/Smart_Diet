<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Suivi
 *
 * @ORM\Table(name="suivi", indexes={@ORM\Index(name="idUser", columns={"idUser"})})
 * @ORM\Entity
 */
class Suivi
{
    /**
     * @var int
     *
     * @ORM\Column(name="idSuivi", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idsuivi;

    /**
     * @var int|null
     *
     * @ORM\Column(name="dateSuivi", type="integer", nullable=true)
     */
    private $datesuivi;

    /**
     * @var float|null
     *
     * @ORM\Column(name="taille", type="float", precision=10, scale=0, nullable=true)
     */
    private $taille;

    /**
     * @var float|null
     *
     * @ORM\Column(name="poid", type="float", precision=10, scale=0, nullable=true)
     */
    private $poid;

    /**
     * @var float|null
     *
     * @ORM\Column(name="glycemie", type="float", precision=10, scale=0, nullable=true)
     */
    private $glycemie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="petitDej", type="string", length=255, nullable=true)
     */
    private $petitdej;

    /**
     * @var string|null
     *
     * @ORM\Column(name="repas", type="string", length=255, nullable=true)
     */
    private $repas;

    /**
     * @var string|null
     *
     * @ORM\Column(name="diner", type="string", length=255, nullable=true)
     */
    private $diner;

    /**
     * @var string|null
     *
     * @ORM\Column(name="notes", type="string", length=255, nullable=true)
     */
    private $notes;

    /**
     * @var \Personne
     *
     * @ORM\ManyToOne(targetEntity="Personne")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     * })
     */
    private $iduser;

    public function getIdsuivi(): ?int
    {
        return $this->idsuivi;
    }

    public function getDatesuivi(): ?int
    {
        return $this->datesuivi;
    }

    public function setDatesuivi(?int $datesuivi): self
    {
        $this->datesuivi = $datesuivi;

        return $this;
    }

    public function getTaille(): ?float
    {
        return $this->taille;
    }

    public function setTaille(?float $taille): self
    {
        $this->taille = $taille;

        return $this;
    }

    public function getPoid(): ?float
    {
        return $this->poid;
    }

    public function setPoid(?float $poid): self
    {
        $this->poid = $poid;

        return $this;
    }

    public function getGlycemie(): ?float
    {
        return $this->glycemie;
    }

    public function setGlycemie(?float $glycemie): self
    {
        $this->glycemie = $glycemie;

        return $this;
    }

    public function getPetitdej(): ?string
    {
        return $this->petitdej;
    }

    public function setPetitdej(?string $petitdej): self
    {
        $this->petitdej = $petitdej;

        return $this;
    }

    public function getRepas(): ?string
    {
        return $this->repas;
    }

    public function setRepas(?string $repas): self
    {
        $this->repas = $repas;

        return $this;
    }

    public function getDiner(): ?string
    {
        return $this->diner;
    }

    public function setDiner(?string $diner): self
    {
        $this->diner = $diner;

        return $this;
    }

    public function getNotes(): ?string
    {
        return $this->notes;
    }

    public function setNotes(?string $notes): self
    {
        $this->notes = $notes;

        return $this;
    }

    public function getIduser(): ?Personne
    {
        return $this->iduser;
    }

    public function setIduser(?Personne $iduser): self
    {
        $this->iduser = $iduser;

        return $this;
    }


}
