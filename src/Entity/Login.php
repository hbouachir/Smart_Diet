<?php
namespace App\Entity;


use PhpParser\Node\Scalar\String_;

class Login{
    /**
     * @var String
     */
    private $nom;

    /**
     * @var String
     */
    private $mdp;

    /**
     * @return String
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param String $nom
     */
    public function setNom(String $nom): self
    {
        $this->nom = $nom;
        return $this;
    }

    /**
     * @return String
     */
    public function getMdp(): ?String
    {
        return $this->mdp;
    }

    /**
     * @var string The hashed password
     * @param String $mdp
     */
    public function setMdp(String $mdp): self
    {
        $this->mdp = $mdp;
        return $this;
    }



}