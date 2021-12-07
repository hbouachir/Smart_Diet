<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produit
 *
 * @ORM\Table(name="produit")
 * @ORM\Entity
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="codeProduit", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $codeproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="quantite", type="string", length=255, nullable=false)
     */
    private $quantite;

    /**
     * @var string
     *
     * @ORM\Column(name="prix", type="string", length=255, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="statut", type="string", length=255, nullable=false)
     */
    private $statut;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie", type="string", length=255, nullable=false)
     */
    private $categorie;

    /**
     * @return int
     */
    public function getCodeproduit(): int
    {
        return $this->codeproduit;
    }

    /**
     * @param int $codeproduit
     * @return Produit
     */
    public function setCodeproduit(int $codeproduit): Produit
    {
        $this->codeproduit = $codeproduit;
        return $this;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     * @return Produit
     */
    public function setNom(string $nom): Produit
    {
        $this->nom = $nom;
        return $this;
    }

    /**
     * @return string
     */
    public function getDescription(): ?string
    {
        return $this->description;
    }

    /**
     * @param string $description
     * @return Produit
     */
    public function setDescription(string $description): Produit
    {
        $this->description = $description;
        return $this;
    }

    /**
     * @return string
     */
    public function getQuantite(): ?string
    {
        return $this->quantite;
    }

    /**
     * @param string $quantite
     * @return Produit
     */
    public function setQuantite(string $quantite): Produit
    {
        $this->quantite = $quantite;
        return $this;
    }

    /**
     * @return string
     */
    public function getPrix(): ?string
    {
        return $this->prix;
    }

    /**
     * @param string $prix
     * @return Produit
     */
    public function setPrix(string $prix): Produit
    {
        $this->prix = $prix;
        return $this;
    }

    /**
     * @return string
     */
    public function getStatut(): ?string
    {
        return $this->statut;
    }

    /**
     * @param string $statut
     * @return Produit
     */
    public function setStatut(string $statut): Produit
    {
        $this->statut = $statut;
        return $this;
    }

    /**
     * @return string
     */
    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    /**
     * @param string $categorie
     * @return Produit
     */
    public function setCategorie(string $categorie): Produit
    {
        $this->categorie = $categorie;
        return $this;
    }


}
