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
     * @var float
     *
     * @ORM\Column(name="prixProduit", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProduit", type="string", length=255, nullable=false)
     */
    private $nomproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionProduit", type="string", length=255, nullable=false)
     */
    private $descriptionproduit;

    public function getCodeproduit(): ?int
    {
        return $this->codeproduit;
    }

    public function getPrixproduit(): ?float
    {
        return $this->prixproduit;
    }

    public function setPrixproduit(float $prixproduit): self
    {
        $this->prixproduit = $prixproduit;

        return $this;
    }

    public function getNomproduit(): ?string
    {
        return $this->nomproduit;
    }

    public function setNomproduit(string $nomproduit): self
    {
        $this->nomproduit = $nomproduit;

        return $this;
    }

    public function getDescriptionproduit(): ?string
    {
        return $this->descriptionproduit;
    }

    public function setDescriptionproduit(string $descriptionproduit): self
    {
        $this->descriptionproduit = $descriptionproduit;

        return $this;
    }


}
