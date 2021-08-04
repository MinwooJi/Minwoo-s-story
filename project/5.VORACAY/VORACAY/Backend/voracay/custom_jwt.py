from rest_framework_jwt.settings import api_settings


def jwt_payload_handler(user):
    return {
        'user_number': user.user_number,
        'user_name': user.user_name
    }


def jwt_response_payload_handler(token, user=None, request=None):
    return {
        'token': token,
        'user': {
            'user_name': user.user_name,
        }
    }


def jwt_get_username_from_payload_handler(payload):
    return payload.get('user_name')
