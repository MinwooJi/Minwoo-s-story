from rest_framework.response import Response
from voracay.serializers import *
from rest_framework import status
from rest_framework import permissions


def getUserName(request):
    serializer = VerifyJSONWebTokenSerializer(data=request.META)
    if not serializer.is_valid(raise_exception=True):
        return 'REQUEST_BODY_ERROR'
    user_name = serializer.validated_data['user_name']
    if user_name == "None":
        return 'PASSWORD_FAIL'
    return user_name


class IsExistJWT(permissions.BasePermission):
    def has_permission(self, request, view):
        is_bearer = request.META.get('HTTP_BEARER', None)
        return is_bearer != None
