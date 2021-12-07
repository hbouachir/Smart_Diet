<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Personne
 *
 * @ORM\Table(name="personne")
 * @ORM\Entity
 */
class Personne
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=false)
     */
    private $prenom;

    /**
     * @var string|null
     *
     * @ORM\Column(name="dateNaissance", type="string", length=255, nullable=true)
     */
    private $datenaissance;

    /**
     * @var string
     *
     * @ORM\Column(name="mail", type="string", length=255, nullable=false)
     */
    private $mail;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=255, nullable=false)
     */
    private $password;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=true)
     */
    private $adresse;

    /**
     * @var int|null
     *
     * @ORM\Column(name="referenceClient", type="integer", nullable=true)
     */
    private $referenceclient;

    /**
     * @var int|null
     *
     * @ORM\Column(name="numero", type="integer", nullable=true)
     */
    private $numero;

    /**
     * @var string|null
     *
     * @ORM\Column(name="civilite", type="string", length=255, nullable=true)
     */
    private $civilite;

    /**
     * @var string
     *
     * @ORM\Column(name="privilege", type="string", length=255, nullable=false)
     */
    private $privilege;

    /**
     * @var string|null
     *
     * @ORM\Column(name="maladie", type="string", length=255, nullable=true)
     */
    private $maladie;

    /**
     * @var int|null
     *
     * @ORM\Column(name="mode", type="integer", nullable=true)
     */
    private $mode;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=true)
     */
    private $image;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     * @return Personne
     */
    public function setId(int $id): Personne
    {
        $this->id = $id;
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
     * @return Personne
     */
    public function setNom(string $nom): Personne
    {
        $this->nom = $nom;
        return $this;
    }

    /**
     * @return string
     */
    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     * @return Personne
     */
    public function setPrenom(string $prenom): Personne
    {
        $this->prenom = $prenom;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getDatenaissance(): ?string
    {
        return $this->datenaissance;
    }

    /**
     * @param string|null $datenaissance
     * @return Personne
     */
    public function setDatenaissance(?string $datenaissance): Personne
    {
        $this->datenaissance = $datenaissance;
        return $this;
    }

    /**
     * @return string
     */
    public function getMail(): ?string
    {
        return $this->mail;
    }

    /**
     * @param string $mail
     * @return Personne
     */
    public function setMail(string $mail): Personne
    {
        $this->mail = $mail;
        return $this;
    }

    /**
     * @return string
     */
    public function getPassword(): ?string
    {
        return $this->password;
    }

    /**
     * @param string $password
     * @return Personne
     */
    public function setPassword(?string $password): Personne
    {
        $this->password = $password;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    /**
     * @param string|null $adresse
     * @return Personne
     */
    public function setAdresse(?string $adresse): Personne
    {
        $this->adresse = $adresse;
        return $this;
    }

    /**
     * @return int|null
     */
    public function getReferenceclient(): ?int
    {
        return $this->referenceclient;
    }

    /**
     * @param int|null $referenceclient
     * @return Personne
     */
    public function setReferenceclient(?int $referenceclient): Personne
    {
        $this->referenceclient = $referenceclient;
        return $this;
    }

    /**
     * @return int|null
     */
    public function getNumero(): ?int
    {
        return $this->numero;
    }

    /**
     * @param int|null $numero
     * @return Personne
     */
    public function setNumero(?int $numero): Personne
    {
        $this->numero = $numero;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getCivilite(): ?string
    {
        return $this->civilite;
    }

    /**
     * @param string|null $civilite
     * @return Personne
     */
    public function setCivilite(?string $civilite): Personne
    {
        $this->civilite = $civilite;
        return $this;
    }

    /**
     * @return string
     */
    public function getPrivilege(): ?string
    {
        return $this->privilege;
    }

    /**
     * @param string $privilege
     * @return Personne
     */
    public function setPrivilege(string $privilege): Personne
    {
        $this->privilege = $privilege;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getMaladie(): ?string
    {
        return $this->maladie;
    }

    /**
     * @param string|null $maladie
     * @return Personne
     */
    public function setMaladie(?string $maladie): Personne
    {
        $this->maladie = $maladie;
        return $this;
    }

    /**
     * @return int|null
     */
    public function getMode(): ?int
    {
        return $this->mode;
    }

    /**
     * @param int|null $mode
     * @return Personne
     */
    public function setMode(?int $mode): Personne
    {
        $this->mode = $mode;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getImage(): ?string
    {
        return $this->image;
    }

    /**
     * @param string|null $image
     * @return Personne
     */
    public function setImage(?string $image): Personne
    {
        $this->image = $image;
        return $this;
    }


}
