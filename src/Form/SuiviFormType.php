<?php

namespace App\Form;

use App\Entity\Personne;
use App\Entity\Suivi;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SuiviFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('taille')
            ->add('poid')
            ->add('glycemie')
            ->add('petitdej')
            ->add('repas')
            ->add('diner')
            ->add('notes')
           # ->add('iduser' , EntityType::class,['class'=> Personne::class,'choice_label'=>'id','empty_data' => '',])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Suivi::class,
        ]);
    }
}
