# from django.contrib.auth.models import AbstractUser
# from django.db import models


# Create your models here.
# 확장성 보장
# class Member(models.Model):
#     m_id = models.AutoField(db_column='m_id', primary_key=True)
#     m_pw = models.CharField(db_column='m_pw',max_length=100)
#     m_name = models.CharField(db_column='m_name',max_length=100)
#     m_email = models.CharField(db_column='m_email',max_length=100)

#     class Meta:
#         managed = False
#         db_table = 'member'

# class JoinInfo(models.Model):
#     j_id = models.AutoField(db_column='j_id', primary_key=True)
#     r_id = models.IntegerField(db_column='r_id')
#     m_id = models.IntegerField(db_column='m_id')


#     class Meta:
#         managed = False
#         db_table = 'joininfo'

# class Room(models.Model):
#     r_id = models.AutoField(db_column='r_id', primary_key=True)
#     m_id = models.IntegerField(db_column='m_id')
#     r_startTime = models.DateField(db_column='r_starttime')
#     r_endTime = models.DateField(db_column='r_endtime')

#     class Meta:
#         managed = False
#         db_table = 'room'

# class RecodeInfo(models.Model):
#     ri_id = models.AutoField(db_column='ri_id', primary_key=True)
#     r_id = models.IntegerField(db_column='r_id')
#     ri_text = models.CharField(db_column='ri_text',max_length=100)


#     class Meta:
#         managed = False
#         db_table = 'recodeinfo'

# class SignLang(models.Model):
#     s_id = models.AutoField(db_column='s_id', primary_key=True)
#     s_name = models.CharField(db_column='s_name',max_length=100)
#     s_path = models.CharField(db_column='s_path',max_length=100)
#     s_type = models.CharField(db_column='s_type',max_length=100)


#     class Meta:
#         managed = False
#         db_table = 'recodeinfo'
from django.db import models
from django.contrib.auth.models import BaseUserManager, AbstractBaseUser, PermissionsMixin
from django.utils import timezone
from django.utils.translation import ugettext_lazy as _


class UserManager(BaseUserManager):
    use_in_migrations = True

    def _create_user(self, email, password, **extra_fields):
        if not email:
            raise ValueError('The given email must be set')
        email = self.normalize_email(email)
        user = self.model(email=email, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_user(self, email, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', False)
        extra_fields.setdefault('is_superuser', False)
        return self._create_user(email, password, **extra_fields)

    def create_superuser(self, email, password, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)

        if extra_fields.get('is_staff') is not True:
            raise ValueError('Superuser must have is_staff=True.')
        if extra_fields.get('is_superuser') is not True:
            raise ValueError('Superuser must have is_superuser=True.')

        return self.create_user(email, password, **extra_fields)


class User(AbstractBaseUser, PermissionsMixin):
    """
    customized User
    """
    email = models.EmailField(
        verbose_name=_('email id'),
        max_length=64,
        unique=True,
        help_text='EMAIL ID.'
    )
    username = models.CharField(
        max_length=30,
    )
    is_staff = models.BooleanField(
        _('staff status'),
        default=False,
        help_text=_(
            'Designates whether the user can log into this admin site.'),
    )
    is_active = models.BooleanField(
        _('active'),
        default=True,
        help_text=_(
            'Designates whether this user should be treated as active. '
            'Unselect this instead of deleting accounts.'
        ),
    )
    date_joined = models.DateTimeField(_('date joined'), default=timezone.now)

    objects = UserManager()

    EMAIL_FIELD = 'email'
    USERNAME_FIELD = 'email'

    class Meta:
        verbose_name = _('user')
        verbose_name_plural = _('users')

    def __str__(self):
        return self.username

    def get_short_name(self):
        return self.email
