<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Experience
 *
 * @ORM\Table(name="experience", indexes={@ORM\Index(name="client_id", columns={"clientId"})})
 * @ORM\Entity(repositoryClass="App\Repository\ExperienceRepository")
 */
class Experience
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
     * @var int
     *
     * @ORM\Column(name="note", type="integer", nullable=false)
     * @Assert\NotBlank
     */
    private $note;

    /**
     * @var string
     *
     * @ORM\Column(name="avis", type="string", length=255, nullable=false)
     */
    private $avis;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="clientId", referencedColumnName="id")
     * })
     */
    private $clientid;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNote(): ?int
    {
        return $this->note;
    }

    public function setNote(int $note): self
    {
        $this->note = $note;

        return $this;
    }

    public function getAvis(): ?string
    {
        return $this->avis;
    }

    public function setAvis(string $avis): self
    {
        $this->avis = $avis;

        return $this;
    }

    public function getClientid(): ?User
    {
        return $this->clientid;
    }

    public function setClientid(?User $clientid): self
    {
        $this->clientid = $clientid;

        return $this;
    }
    public function __toString() {
        return $this->getClientid()->getPrenom().''.$this.$this->getClientid()->getNom().' '.$this->getAvis();
    }



}
