<?php

namespace App\Form;

use App\Entity\Personne;
use App\Entity\Reclamation;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Doctrine\ORM\EntityRepository;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Doctrine\Persistence\ManagerRegistry;

use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {



        $builder
            //->add('message', TextType::class)
            ->add('description',TextareaType::class,[
                'label'=>'description',
                'attr'=>['placeholder'=>'description'
                ]
            ])
            ->add('typereclamation',ChoiceType::class,[
                'label'=>'type reclamation',
                'choices'  => [
                    'Produit' => null,
                    'Livraison' => true,
                    'Nutritionniste' => false,
                ],
            ])

            //->add('idpersonne')
            ->add('idpersonne', null, array(
                'class' => Personne::class,
                'query_builder' => function (EntityRepository $er) {
                    return $er->createQueryBuilder('u')
                        ->where("u.privilege='client'");
                    //->setParameter('id', '1')
                    // ->orderBy('u.username', 'ASC');
                },
                //'choice_label' => 'username',
                'required' => false,
            ))




            ->add("captchaCode",CaptchaType::class,[
                'captchaConfig'=>'ExampleCaptchaUserRegistration',
                'constraints'=>[
                    new ValidCaptcha([
                        'message'=>'Invalid captcha, Please try again'
                    ])
                ]
            ])

           // ->add("searchbox")
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
