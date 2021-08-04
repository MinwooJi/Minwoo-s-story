from django.db import models
from django.utils import timezone

# Create your models here.


class User(models.Model):
    user_number = models.AutoField(primary_key=True)
    user_name = models.CharField(max_length=30)
    user_grade = models.CharField(max_length=10)
    user_dept = models.CharField(max_length=45)
    user_email = models.EmailField(max_length=128)
    user_password = models.CharField(max_length=32)

    def __str__(self):
        return f"{self.user_number} {self.user_name} {self.user_grade}"


class Group(models.Model):
    group_id = models.AutoField(primary_key=True)
    group_name = models.CharField(max_length=32)
    group_desc = models.CharField(max_length=128)
    group_manager = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.group_name}"


class GroupManage(models.Model):
    group_id = models.ForeignKey(Group, on_delete=models.CASCADE)
    user_number = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.group_id} {self.user_number}"


class Favorites(models.Model):
    user_number = models.ForeignKey(User, on_delete=models.CASCADE)
    favor_user_number = models.IntegerField()

    def __str__(self):
        return f"{self.user_number} {self.favor_user_number}"


class Meeting(models.Model):
    meeting_id = models.AutoField(primary_key=True)
    open_user_number = models.ForeignKey(User, on_delete=models.CASCADE)
    meeting_open_data = models.DateTimeField(auto_now_add=True)
    meeting_link = models.CharField(max_length=128)

    def __str__(self):
        return f"{self.meeting_id} {self.open_user_number}"


class LogMeetingUsers(models.Model):
    meeting_id = models.ForeignKey(Meeting, on_delete=models.CASCADE)
    user_number = models.ForeignKey(User, on_delete=models.CASCADE)


class LogMettingChat(models.Model):
    meeting_id = models.ForeignKey(Meeting, on_delete=models.CASCADE)
    meeting_chat_url = models.CharField(max_length=128)


class LogCall(models.Model):
    call_id = models.AutoField(primary_key=True)
    caller_user_number = models.ForeignKey(User, on_delete=models.CASCADE)
    call_start_time = models.DateTimeField(auto_now_add=True)
    call_end_time = models.DateTimeField(auto_now_add=False)

    def __str__(self):
        return f"{self.call_id} {self.caller_user_number}"


class LogCallUser(models.Model):
    call_id = models.ForeignKey(LogCall, on_delete=models.CASCADE)
    user_number = models.ForeignKey(User, on_delete=models.CASCADE)
