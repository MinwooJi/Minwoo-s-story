from django.db import models
from accounts.models import User
from django.conf import settings

# Create your models here.


class Room(models.Model):
    r_id = models.AutoField(primary_key=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL,
                             on_delete=models.CASCADE)
    r_startTime = models.DateTimeField(auto_now_add=True)
    r_endTime = models.DateTimeField(auto_now=True)

    # def __str__(self):
    #     return "[r_id:" + self.r_id + "user:" + self.user + "r_startTime:" + self.r_startTime + "r_endTime:" + self.r_endTime


class JoinInfo(models.Model):
    j_id = models.AutoField(primary_key=True)
    room = models.ForeignKey(Room,
                             on_delete=models.CASCADE, null=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL,
                             on_delete=models.CASCADE, null=True)


class RecodeInfo(models.Model):
    ri_id = models.AutoField(primary_key=True)
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    ri_text = models.TextField()



