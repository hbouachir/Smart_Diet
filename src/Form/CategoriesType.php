<?php

namespace App\Form;

use App\Entity\Categories;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CategoriesType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom',TextType::class,[
                'label'=>'Nom categories',
                'attr'=>[
                    'placeholder'=>'Merci de définir le nom'
                ]
            ])
            ->add('description',TextType::class,[
                'label'=>'Description',
                'attr'=>[
                    'placeholder'=>'Description'
                ]
            ])
            ->add('statut',TextType::class,[

                'label'=>'statut',
                'attr'=>[
                    'placeholder'=>'statut' ]

            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Categories::class,
        ]);
    }
}
