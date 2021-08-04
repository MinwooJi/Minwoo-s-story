from django.db.models.fields.related import ManyToManyField
from django.core.exceptions import ObjectDoesNotExist
from voracay.models import *


def to_dict(instance):
    opts = instance._meta
    data = {}
    for f in opts.concrete_fields + opts.many_to_many:
        if isinstance(f, ManyToManyField):
            if instance.pk is None:
                data[f.name] = []
            else:
                data[f.name] = list(f.value_from_object(
                    instance).value_list('pk', flat=True))
        else:
            data[f.name] = f.value_from_object(instance)
    return data


def authenticateUserLogin(user_number, user_password):
    try:
        user = User.objects.get(pk=user_number)
        if user.user_password == user_password:
            return user
        else:
            return 'PASSWORD_FAIL'
    except ObjectDoesNotExist:
        return None
