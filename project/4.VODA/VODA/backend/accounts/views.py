from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.generics import RetrieveAPIView

from .serializers import UserCreateSerializer, UserLoginSerializer, UserSerializer
from .models import User


@api_view(['POST'])
@permission_classes([AllowAny])
def createUser(request):
    if request.method == 'POST':
        serializer = UserCreateSerializer(data=request.data)
        if not serializer.is_valid(raise_exception=True):
            return Response({"message": "Request Body Error."}, status=status.HTTP_409_CONFLICT)

        if User.objects.filter(email=serializer.validated_data['email']).first() is None:
            serializer.save()
            return Response({"message": "ok"}, status=status.HTTP_201_CREATED)
        return Response({"message": "duplicate email"}, status=status.HTTP_409_CONFLICT)


@api_view(['POST'])
@permission_classes([AllowAny])
def login(request):
    if request.method == 'POST':
        serializer = UserLoginSerializer(data=request.data)

        if not serializer.is_valid(raise_exception=True):
            return Response({"message": "Request Body Error."}, status=status.HTTP_409_CONFLICT)
        if serializer.validated_data['email'] == "None":
            return Response({'message': 'fail'}, status=status.HTTP_200_OK)

        response = {
            'message': 'True',
            'token': serializer.data['token'],
            # 'email': serializer.data['email'],
            # 'username': serializer.data['username']
        }
        return Response(response, status=status.HTTP_200_OK)

@api_view(['POST'])
@permission_classes([AllowAny])
def info(request):
    if request.method == 'POST':     
        return Response({
            "email": request.user.email,
            "username" : request.user.username,
            "id" : request.user.id
        }, status=status.HTTP_200_OK)
