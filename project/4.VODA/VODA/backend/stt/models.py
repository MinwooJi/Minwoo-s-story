from django.db import models

# Create your models here.
class SignLang(models.Model):
    S_ID = models.AutoField(primary_key=True)
    S_NAME = models.CharField(max_length=100)
    S_PATH = models.CharField(max_length=255)
    S_TYPE = models.CharField(max_length=255)