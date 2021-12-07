<?php

namespace App\Form;

use App\Entity\Personne;

use Symfony\Component\Form\AbstractType;

use Symfony\Component\Form\Extension\Core\Type\BirthdayType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Symfony\Component\Validator\Constraints\NotBlank;

class ClientupadForm extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', TextType::class)
            ->add('prenom',TextType::class,['required' => true])
            ->add('datenaissance')
            ->add('mail',TextType::class,['required' => true])


            ->add('adresse',TextType::class,['required' => true])
            ->add('referenceclient',IntegerType::class,['attr' => ['maxlength' => 4]])
            ->add('numero')
            ->add('civilite', ChoiceType::class, array(
                'choices'  => array(
                    'Homme' => 'H',
                    'Femme' => 'F'
                ),'expanded'=>true))
            ->add('privilege' , ChoiceType::class, array(
                'choices'  => array(
                    'Client' => 'client',

                ),'expanded'=>true))
            ->add('maladie')
            ->add('mode')
            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptchaUserRegistration',
                'constraints' => [
                    new ValidCaptcha([
                        'message' => 'Invalid captcha, please try again',
                    ]),
                ],
            ))
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Personne::class,
        ]);
    }
}
