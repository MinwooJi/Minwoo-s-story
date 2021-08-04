from rest_framework import serializers
from accounts.models import User
from accounts.serializers import UserSerializer
from .models import (Room, JoinInfo, RecodeInfo)


class RoomSerializer(serializers.ModelSerializer):
    user = UserSerializer(required=False)

    class Meta:
        model = Room
        fields = '__all__'


class JoinInfoSerializer(serializers.ModelSerializer):
    user = UserSerializer(required=False)
    room = RoomSerializer(required=False)

    class Meta:
        model = JoinInfo
        fields = '__all__'
