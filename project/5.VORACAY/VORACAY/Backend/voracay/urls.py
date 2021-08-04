from django.urls import path
from voracay import views

urlpatterns = [
    path('api/v2/login/', views.LoginAPIView.as_view()),
    path('api/v2/users/', views.UserAPIView.as_view()),
    path('api/v2/check/', views.CheckAPIView.as_view()),
    path('api/v2/groups/', views.GroupAPIView.as_view()),
    path('api/v2/groups/<int:group_id>', views.GroupDetailAPIView.as_view()),
    path('api/v2/favor/', views.FavorAPIView.as_view()),
    path('api/v2/test', views.TestAPIView.as_view()),
]
