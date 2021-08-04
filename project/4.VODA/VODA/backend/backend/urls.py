from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/users/', include('accounts.urls')),
    path('api/room/', include('room.urls')),
    path('api/stt/', include('stt.urls')),
    path('api/hand/', include('hand.urls')),
]
