<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\Personne;
use phpDocumentor\Reflection\Types\Integer;

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
     * @var string|null
     *
     * @ORM\Column(name="dateSuivi", type="string", length=255, nullable=true)
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
     * @ORM\ManyToOne(targetEntity="Personne",inversedBy="suivis")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     * })
     */
    private $iduser;

    /**
     * @return int
     */
    public function getIdsuivi(): int
    {
        return $this->idsuivi;
    }

    /**
     * @param int $idsuivi
     * @return Suivi
     */
    public function setIdsuivi(int $idsuivi): Suivi
    {
        $this->idsuivi = $idsuivi;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getDatesuivi(): ?string
    {
        return $this->datesuivi;
    }

    /**
     * @param string|null $datesuivi
     * @return Suivi
     */
    public function setDatesuivi(?string $datesuivi): Suivi
    {
        $this->datesuivi = $datesuivi;
        return $this;
    }



    /**
     * @return float|null
     */
    public function getTaille(): ?float
    {
        return $this->taille;
    }

    /**
     * @param float|null $taille
     * @return Suivi
     */
    public function setTaille(?float $taille): Suivi
    {
        $this->taille = $taille;
        return $this;
    }

    /**
     * @return float|null
     */
    public function getPoid(): ?float
    {
        return $this->poid;
    }

    /**
     * @param float|null $poid
     * @return Suivi
     */
    public function setPoid(?float $poid): Suivi
    {
        $this->poid = $poid;
        return $this;
    }

    /**
     * @return float|null
     */
    public function getGlycemie(): ?float
    {
        return $this->glycemie;
    }

    /**
     * @param float|null $glycemie
     * @return Suivi
     */
    public function setGlycemie(?float $glycemie): Suivi
    {
        $this->glycemie = $glycemie;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getPetitdej(): ?string
    {
        return $this->petitdej;
    }

    /**
     * @param string|null $petitdej
     * @return Suivi
     */
    public function setPetitdej(?string $petitdej): Suivi
    {
        $this->petitdej = $petitdej;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getRepas(): ?string
    {
        return $this->repas;
    }

    /**
     * @param string|null $repas
     * @return Suivi
     */
    public function setRepas(?string $repas): Suivi
    {
        $this->repas = $repas;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getDiner(): ?string
    {
        return $this->diner;
    }

    /**
     * @param string|null $diner
     * @return Suivi
     */
    public function setDiner(?string $diner): Suivi
    {
        $this->diner = $diner;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getNotes(): ?string
    {
        return $this->notes;
    }

    /**
     * @param string|null $notes
     * @return Suivi
     */
    public function setNotes(?string $notes): Suivi
    {
        $this->notes = $notes;
        return $this;
    }

    /**
     * @return \Personne
     */
    public function getIduser(): ?Personne
    {
        return $this->iduser;
    }

    /**
     * @param Personne $iduser
     * @return Suivi
     * self
     */
    public function setIduser(Personne $iduser):self
    {
        $this->iduser = $iduser;
        return $this;
    }



}
