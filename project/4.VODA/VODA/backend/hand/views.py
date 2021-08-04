from django.shortcuts import render
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.generics import RetrieveAPIView
import tensorflow as tf
import cv2 as cv
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image as image2
from PIL import Image
from io import BytesIO
import os
import numpy as np
# Create your views here.
model = load_model("model1.h5")
@api_view(['POST'])
@permission_classes([AllowAny])
def recognize(request):
    print("파이썬 서버 수신")
    ff = request.data['file']
    img = Image.open(ff)
    resize_image = img.resize((200, 200))
    res = find(resize_image)
    print("판별결과는" + res)

    return Response(res)


def load_image(img):
    img_tensor = image2.img_to_array(img)
    img_tensor = np.expand_dims(img_tensor, axis=0)
    img_tensor /= 255.
    return img_tensor


def find(filename):
    alphabet = [
        "ㄱ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ",
        "ㄴ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅢ", "ㄷ",
        "ㅚ", "ㅟ", "nothing", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ"]
    # print(model.predict(load_image(filename)))
    return alphabet[np.argmax(model.predict(load_image(filename)))]


