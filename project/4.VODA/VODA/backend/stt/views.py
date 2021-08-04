from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.generics import RetrieveAPIView

import kss
from konlpy.tag import Komoran
from .models import SignLang
komoran = Komoran()

# Create your views here.
@api_view(['POST'])
@permission_classes([AllowAny])
def stt(request):
    # print("받아온 음성->텍스트 데이터 : ",request.data)
    a=[]
    b=[]
    for sent in kss.split_sentences(request.data['text']):
        a.append(komoran.pos(sent))
    print("형태소 분석 데이터 : ",a)
    for t in a[0]:
        print(t)
        # 명사
        if t[1] == 'NNG' or t[1] =='NNP' or t[1] =='NNB':
            t = (t[0],'NN')
        # 조사
        elif t[1] == 'JKS' or t[1] =='JKC' or t[1] =='JKG' or t[1] =='JKO' or t[1] =='JKB' or t[1] =='JKV' or t[1] =='JKQ' or t[1] =='JX' or t[1] =='JC':
           t = (t[0],'JK')
        # 어미
        elif t[1] == 'EC' or t[1] =='ETN' or t[1] =='ETM' or t[1] =='EM' :
            t = (t[0],'EP')
        # 부사
        elif t[1] == 'MAJ' or t[1] =='AD' :
            t = (t[0],'MAG')
        # 접사
        elif t[1] == 'XSV' or t[1] =='XSA' :
            t = (t[0],'XSN')
        print("***바꾼 t",t)
        # 이름만 비교
        qs = SignLang.objects.filter(S_NAME=t[0]).values('S_TYPE','S_NAME','S_PATH')
        print("qs는 ??",qs)
        if len(qs) != 0 :
            if len(qs) == 1 :
                print("이름 비교시 결과값이 한 개")
                b.append(qs[0]['S_PATH'])
            #이름 비교시에 여러개의 값이 나왔다면 ?
            else :
                qs2 = qs.filter(S_TYPE=t[1]).values('S_TYPE','S_NAME','S_PATH')
                print("qs2는 ??",qs)
                # 품사와 맞는 값이 있다
                if len(qs2) != 0:
                    print("이름 비교시 결과값이 여러 개이고 품사까지 맞다")
                    b.append(qs2[0]['S_PATH'])
                else:
                    print("품사가 달라서 첫 번째껄 리턴")
                    b.append(qs[0]['S_PATH'])
    return Response(b)