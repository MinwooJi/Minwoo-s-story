from django.core.exceptions import ObjectDoesNotExist
from rest_framework import serializers
from rest_framework_jwt.settings import api_settings
from voracay.custom_jwt import jwt_payload_handler, jwt_get_username_from_payload_handler
from voracay.models import *
from voracay.util import authenticateUserLogin
from collections import OrderedDict
import jwt

JWT_PAYLOAD_HANDLER = jwt_payload_handler
JWT_ENCODE_HANDLER = api_settings.JWT_ENCODE_HANDLER
JWT_DECODE_HANDLER = api_settings.JWT_DECODE_HANDLER
JWT_PAYLOAD_GET_USERNAME_HANDLER = jwt_get_username_from_payload_handler


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['user_number', 'user_name', 'user_grade',
                  'user_dept', 'user_email']


class UserNameSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['user_name']


class GroupSerializer(serializers.ModelSerializer):

    class Meta:
        model = Group
        fields = ['group_id', 'group_name', 'group_desc', 'group_manager']

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['group_manager'] = UserNameSerializer(
            instance.group_manager).data
        return response


class GroupIdSerializer(serializers.ModelSerializer):
    class Meta:
        model = Group
        fields = ['group_id']


class GroupManageSerializer(serializers.ModelSerializer):
    class Meta:
        model = GroupManage
        fields = ['group_id', 'user_number']

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['user_number'] = UserSerializer(
            instance.user_number
        ).data
        return response


class GroupManageUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = GroupManage
        fields = ['user_number']

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['user_number'] = UserSerializer(
            instance.user_number
        ).data
        return response


class FavorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Favorites
        fields = ['user_number', 'favor_user_number']


class UserLoginSerializer(serializers.Serializer):
    user_number = serializers.IntegerField()
    user_password = serializers.CharField(max_length=32, write_only=True)
    token = serializers.CharField(max_length=255, read_only=True)

    def validate(self, data):
        user_number = data.get('user_number', None)
        user_password = data.get('user_password', None)
        user = authenticateUserLogin(user_number, user_password)
        if user is None:
            return {
                'user_name': 'None'
            }
        if user == 'PASSWORD_FAIL':
            return {
                'user_name': 'FAIL'
            }
        try:
            payload = JWT_PAYLOAD_HANDLER(user)
            jwt_token = JWT_ENCODE_HANDLER(payload)
        except ObjectDoesNotExist:
            raise serializers.ValidationError(
                'User with given user_number and user_password does not exists'
            )
        return {
            'user_name': user.user_name,
            'token': jwt_token
        }


class VerifyJSONWebTokenSerializer(serializers.Serializer):
    HTTP_BEARER = serializers.CharField(max_length=255, write_only=True)
    token = serializers.CharField(max_length=255, read_only=True)
    # user_name = serializers.CharField(max_length=30)

    def validate(self, data):
        HTTP_BEARER = data.get('HTTP_BEARER', None)

        if HTTP_BEARER is None:
            return {
                'user_name': 'None'
            }

        payload = self._check_payload(token=HTTP_BEARER)

        try:
            user_name = JWT_PAYLOAD_GET_USERNAME_HANDLER(payload)
        except ObjectDoesNotExist:
            raise serializers.ValidationError(
                'User with given user_number and user_password does not exists'
            )
        return {
            'user_name': user_name
        }

    def _check_payload(self, token):
        try:
            payload = JWT_DECODE_HANDLER(token)
        except jwt.ExpiredSignature:
            msg = 'Signature has expired'
            raise serializers.ValidationError(msg)
        except jwt.DecodeError:
            msg = 'Error decoding signature.'
            raise serializers.ValidationError(msg)
        return payload
