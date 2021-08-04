import json
from collections import OrderedDict
from voracay.util import *
from voracay.serializers import *
from voracay.models import *
from voracay.permissions import *
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import login, authenticate
from django.core.serializers.json import DjangoJSONEncoder
from django.core.exceptions import ObjectDoesNotExist
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.generics import get_object_or_404
from rest_framework.permissions import AllowAny
from rest_framework import status

# Create your views here.


class LoginAPIView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = UserLoginSerializer(data=request.data)
        if not serializer.is_valid(raise_exception=True):
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if serializer.validated_data['user_name'] == "None":
            print('NON_EXIST_USER_NUMBER')
            return Response({
                'message': 'NON_EXIST_USER_NUMBER'
            }, status=status.HTTP_200_OK)

        if serializer.validated_data['user_name'] == 'FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        response = {
            'message': 'success',
            'user_name': serializer.validated_data['user_name'],
            'token': serializer.validated_data['token']
        }

        return Response(response, status=status.HTTP_200_OK)


class UserAPIView(APIView):
    permission_classes = [IsExistJWT]

    def get(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        category = request.query_params.get('category', None)
        search = request.query_params.get('search', None)
        page = int(request.query_params.get('page', 1))
        users = User.objects.all()
        if category == "user_name":
            users = User.objects.filter(user_name__contains=search)
        elif category == 'user_number':
            users = User.objects.filter(user_number__contains=search)
        elif category == "user_grade":
            users = User.objects.filter(user_grade__contains=search)
        elif category == "user_dept":
            users = User.objects.filter(user_dept__contains=search)
        elif category == "user_email":
            users = User.objects.filter(user_email__contains=search)

        paginated_by = 10
        total_count = len(users)
        start_index = paginated_by * (page-1)
        end_index = paginated_by * page

        users = users[start_index:end_index]
        serializer = UserSerializer(users, many=True)

        user = User.objects.get(user_name=user_name)
        favorites = OrderedDict()
        if Favorites.objects.filter(user_number=user).exists():
            test_favorites = Favorites.objects.filter(
                user_number=user)

            test_serial_favorites = FavorSerializer(
                test_favorites, many=True).data
            favorites = [item['favor_user_number']
                         for item in test_serial_favorites]
            response = {
                'message': 'success',
                'content': serializer.data,
                'favorites': favorites,
                'total_count': total_count
            }
            return Response(response, status=status.HTTP_200_OK)
        else:
            response = {
                'message': 'success',
                'content': serializer.data,
                'total_count': total_count
            }
            return Response(response, status=status.HTTP_200_OK)


class CheckAPIView(APIView):
    permeission_classes = [IsExistJWT]

    def post(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        category = request.data.get('category', None)
        if category is None:
            print('REQUIRE_CATEGORY')
            return Response({
                'message': 'REQUIRE_CATEGORY'
            }, status=status.HTTP_200_OK)

        search = request.data.get('search', None)
        if search is None:
            print('REQUIRE_SEARCH')
            return Response({
                'message': 'REQUIRE_SEARCH'
            }, status=status.HTTP_200_OK)

        if category == 'group_name':
            if Group.objects.filter(group_name=search).exists():
                print('DUPLICATE_GROUP_NAME')
                return Response({
                    'message': 'DUPLICATE_GROUP_NAME'
                }, status=status.HTTP_200_OK)

        response = {
            'message': 'NON_DUPLICATE_GROUP_NAME'
        }
        return Response(response, status=status.HTTP_200_OK)


class GroupAPIView(APIView):
    permission_classes = [IsExistJWT]

    def get(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        category = request.query_params.get('category', None)
        search = request.query_params.get('search', None)
        page = int(request.query_params.get('page', 1))

        user = User.objects.get(user_name=user_name)
        group_manager_groups = GroupManage.objects.filter(
            user_number=user.user_number)

        groups_id = [GroupManageSerializer(
            group_manager).data['group_id'] for group_manager in group_manager_groups]
        groups = OrderedDict()
        groups = [GroupSerializer(Group.objects.get(
            group_id=group_id)).data for group_id in groups_id]
        members = OrderedDict()
        members = [GroupManageSerializer(GroupManage.objects.filter(
            group_id=group_id), many=True).data for group_id in groups_id]

        if category == 'group_name':
            for idx, group in enumerate(groups):
                if search not in group.group_name:
                    del groups[idx]
                    del members[idx]

        paginated_by = 10
        total_count = len(groups)
        start_index = paginated_by * (page-1)
        end_index = paginated_by * page
        groups = groups[start_index:end_index]
        response = {
            'message': 'success',
            'content': {
                'group': groups,
                'members': members
            },
            'total_count': total_count
        }
        return Response(response, status=status.HTTP_200_OK)

    def post(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        group = request.data.get('group', None)
        members = request.data.get('members', None)

        group_manager = User.objects.get(
            user_name=user_name)

        new_group = Group(
            group_name=group['group_name'],
            group_desc=group['group_desc'],
            group_manager=group_manager
        )
        new_group.save()

        for member in members:
            new_member = User.objects.get(pk=member['user_number'])
            new_group_manage = GroupManage(
                group_id=new_group,
                user_number=new_member
            )
            new_group_manage.save()

        GroupManage(group_id=new_group, user_number=group_manager).save()

        groups = Group.objects.all()
        gserializer = GroupSerializer(groups, many=True)

        response = {
            'message': 'success',
            'content': gserializer.data
        }

        return Response(response, status=status.HTTP_200_OK)


class GroupDetailAPIView(APIView):
    permission_classes = [IsExistJWT]

    def get(self, request, group_id):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        try:
            group = Group.objects.get(pk=group_id)
        except ObjectDoesNotExist:
            print('NOT_EXIST_GROUP')
            return Response({
                'message': 'NOT_EXIST_GROUP'
            }, status=status.HTTP_200_OK)

        group_members = GroupManage.objects.filter(group_id=group_id)

        gserializer = GroupSerializer(group)
        gmserializer = GroupManageSerializer(group_members, many=True)

        response = {
            'message': 'success',
            'content': {
                'group': gserializer.data,
                'members': gmserializer.data,
            }
        }

        return Response(response, status=status.HTTP_200_OK)

    def post(self, request, group_id):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)
        try:
            group = Group.objects.get(pk=group_id)
        except ObjectDoesNotExist:
            print('NOT_EXIST_GROUP')
            return Response({
                'message': 'NOT_EXIST_GROUP'
            }, status=status.HTTP_200_OK)
        if user_name != group.group_manager.user_name:
            print('NOT_GROUP_MANAGER')
            return Response({
                'message': 'NOT_GROUP_MANAGER'
            }, status=status.HTTP_200_OK)

        member_list = request.data.get('members', None)
        if member_list is None:
            print('REQUIRE_MEMBER')
            return Response({
                'message': 'REQUIRE_MEMBER'
            }, status=status.HTTP_200_OK)

        for members in member_list:
            try:
                user = User.objects.get(pk=members['user_number'])
            except ObjectDoesNotExist:
                print('NOT_EXIST_USER')
                return Response({
                    'message': "NOT_EXIST_USER"
                }, status=status.HTTP_200_OK)
            GroupManage(group_id=group_id, user_number=user).save()

        response = {
            'message': 'success'
        }
        return Response(response, status=status.HTTP_200_OK)

    def put(self, request, group_id):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        try:
            group = Group.objects.get(pk=group_id)
        except ObjectDoesNotExist:
            print('NOT_EXIST_GROUP')
            return Response({
                'message': 'NOT_EXIST_GROUP'
            }, status=status.HTTP_200_OK)

        if user_name != group.group_manager.user_name:
            print('NOT_MANAGER')
            return Response({
                'message': 'NOT_MANAGER'
            }, status=status.HTTP_200_OK)

        group_desc = request.data.get('group_desc', None)
        if group_desc is None:
            print('GROUP_DESC_NONE')
            return Response({
                'message': 'GROUP_DESC_NONE'
            }, status=status.HTTP_200_OK)

        group.group_desc = group_desc
        group.save()

        response = {
            'message': 'success'
        }
        return Response(response, status=status.HTTP_200_OK)

    def delete(self, request, group_id):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        try:
            group = Group.objects.get(pk=group_id)
        except ObjectDoesNotExist:
            print('NOT_EXIST_GROUP')
            return Response({
                'message': 'NOT_EXIST_GROUP'
            }, status=status.HTTP_200_OK)

        if user_name == group.group_manager.user_name:
            del_user = request.query_params.get('user_number', None)
            if del_user is None:
                GroupManage.objects.filter(group_id=group_id).delete()
                group.delete()
            else:
                try:
                    user = User.objects.get(pk=del_user)
                except ObjectDoesNotExist:
                    print('NOT_EXIST_USER')
                    return Response({
                        'message': "NOT_EXIST_USER"
                    }, status=status.HTTP_200_OK)
                GroupManage.objects.filter(
                    group_id=group_id, user_number=user.user_number).delete()

        else:
            user = User.objects.get(user_name=user_name)
            try:
                group_manage = GroupManage.objects.get(group_id=group_id,
                                                       user_number=user.user_number)
            except ObjectDoesNotExist:
                print('NOT_GROUP_MEMBER')
                return Response({
                    'message': "NOT_GROUP_MEMBER"
                }, status=status.HTTP_200_OK)
            group_manage.delete()

        response = {
            'message': 'success'
        }

        return Response(response, status=status.HTTP_200_OK)


class FavorAPIView(APIView):
    permission_classes = [IsExistJWT]

    def get(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        user = User.objects.get(user_name=user_name)

        if Favorites.objects.filter(user_number=user).exists():
            category = request.query_params.get('category', None)
            search = request.query_params.get('search', None)
            page = int(request.query_params.get('page', 1))

            favorites = Favorites.objects.filter(
                user_number=user)
            serial_favorites = FavorSerializer(
                favorites, many=True).data
            favorites = [item['favor_user_number']
                         for item in serial_favorites]

            members_serial = [UserSerializer(User.objects.get(
                user_number=favor)).data for favor in favorites]
            if category == "user_name":
                members_serial = [UserSerializer(User.objects.get(
                    user_number=favor, user_name__contains=search)).data for favor in favorites]
            elif category == 'user_number':
                members_serial = [UserSerializer(User.objects.get(
                    user_number=favor)).data for favor in favorites]
            elif category == "user_grade":
                members_serial = [UserSerializer(User.objects.get(
                    user_number=favor, user_grade__contains=search)).data for favor in favorites]
            elif category == "user_dept":
                members_serial = [UserSerializer(User.objects.get(
                    user_number=favor, user_dept__contains=search)).data for favor in favorites]
            elif category == "user_email":
                members_serial = [UserSerializer(User.objects.get(
                    user_number=favor, user_email__contains=search)).data for favor in favorites]

            paginated_by = 10
            total_count = len(members_serial)
            start_index = paginated_by * (page-1)
            end_index = paginated_by * page
            members_serial = members_serial[start_index:end_index]

            members = OrderedDict()
            members = members_serial

            response = {
                'message': 'success',
                'members': members,
                'total_count': total_count
            }
            return Response(response, status=status.HTTP_200_OK)
        else:
            response = {
                'message': 'success',
                'members': 'None'
            }
            return Response(response, status=status.HTTP_200_OK)

    def post(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        favor_user_number = request.data.get('user_number', None)
        if favor_user_number is None:
            print('REQUIRE_USER_NUMBER')
            return Response({
                'message': 'REQUIRE_USER_NUMBER'
            }, status=status.HTTP_200_OK)

        user = User.objects.get(user_name=user_name)
        Favorites(user_number=user,
                  favor_user_number=favor_user_number).save()

        response = {
            'message': 'success',
        }

        return Response(response, status=status.HTTP_200_OK)

    def delete(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        user = User.objects.get(user_name=user_name)

        del_member = request.query_params.get('user_number', None)
        if del_member is None:
            print('REQUIRE_DEL_MEMBER')
            return Response({
                'message': 'REQUIRE_DEL_MEMBER'
            }, status=status.HTTP_200_OK)

        Favorites.objects.get(
            user_number=user, favor_user_number=del_member).delete()

        response = {
            'message': 'success'
        }
        return Response(response, status=status.HTTP_200_OK)


class TestAPIView(APIView):
    permission_classes = [IsExistJWT]

    def get(self, request):
        user_name = getUserName(request)
        if user_name == 'Request Body Error.':
            print('REQEUST_BODY_ERROR')
            return Response({
                'message': 'REQEUST_BODY_ERROR'
            }, status=status.HTTP_200_OK)
        if user_name == 'PASSWORD_FAIL':
            print('PASSWORD_FAIL')
            return Response({
                'message': 'PASSWORD_FAIL'
            }, status=status.HTTP_200_OK)

        group = Group.objects.get(pk=1)

        print('-'*20)
        print(user_name)
        print(group.group_manager.user_name)
        print('-'*20)

        response = {
            'message': 'success'
        }

        return Response(response, status=status.HTTP_200_OK)
