from django.shortcuts import render
from django.shortcuts import get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
# 인증됨?
from rest_framework.permissions import IsAuthenticated
from accounts.serializers import UserSerializer
from accounts.models import User
from .serializers import RoomSerializer
# Create your views here.


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def create_room(request):
    serializer = RoomSerializer(data=request.data)

    if serializer.is_valid(raise_exception=True):
        serializer.save(user=request.user)  # 요청보낸 사용자를 저장
        return Response(serializer.data)

