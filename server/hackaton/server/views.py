from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response, get_object_or_404
from server.models import *
from django.core import serializers
from decimal import *

def get_player(player):
    return get_object_or_404(Player, nick=player)

def getPlayerInfo(request, player):
    return HttpResponse(serializers.serialize("json", [get_player(player)], fields=('nick','lat','lon','health','exp','level'), ensure_ascii=False),
       content_type='application/json')

def getPlayersInSurrounding(request):
    sector = get_player(request.GET['player']).sector
    players = []
    for i in sector.players.all():
      if i.nick!=request.GET['player']:
        players.append(i)
    return HttpResponse(serializers.serialize("json", players, fields=('nick','lat','lon','health','exp','level'), ensure_ascii=False), 
       content_type='application/json')

def attackPlayer(request, player):
    attack = Attack(attacker = get_player(request.GET['player']), attacked=get_player(player))
    attack.save()
    return HttpResponse("{}", content_type='application/json')

def getAttacksInitiated(request):
    result = Attack.objects.filter(attacker = get_player(request.GET['player']))
    for i in result:
      if i.status!=0:
        i.delete()
    return HttpResponse(serializers.serialize("json", result, ensure_ascii=False), content_type='application/json')

def getAttacksBeingAttacked(request):
    result = Attack.objects.filter(attacked = get_player(request.GET['player']))
    return HttpResponse(serializers.serialize("json", result, ensure_ascii=False), content_type='application/json')

def acceptAttack(request, id):
    attack = Attack.objects.get(pk = id)
    attack.amount = 10
    attack.status = 1
    if request.GET.get('critical'):
      attack.amount = 30
      attack.status = 4
    attack.save()
    player = get_player(request.GET['player'])
    player.health = player.health - attack.amount
    player.save()
    return HttpResponse("{}", content_type='application/json')

def dismissAttack(request, id):
    attack = Attack.objects.get(pk = id)
    attack.amount = 0
    attack.status = 2
    attack.save()
    return HttpResponse("{}", content_type='application/json')

def updatePosition(request):
    player = get_player(request.GET['player'])
    player.lat = Decimal(request.GET['lat'])
    player.lon = Decimal(request.GET['lon'])
    sectorLat = player.lat.quantize(Decimal('.05'), rounding=ROUND_DOWN)
    sectorLon = player.lon.quantize(Decimal('.1'), rounding=ROUND_DOWN)
    try:
      sector = Sector.objects.get(lon = sectorLon, lat = sectorLat)
    except:
      sector = Sector(lon = sectorLon, lat = sectorLat, type = SectorType.objects.get(pk=1)) # TODO: FIX TYPE
    sector.save()
    oldSector = player.sector
    player.sector = sector
    player.save()
    if len(oldSector.players.all())==0:
      oldSector.delete()
    return HttpResponse("{}", content_type='application/json')
