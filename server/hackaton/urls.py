from django.conf.urls.defaults import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'hackaton.views.home', name='home'),
    # url(r'^hackaton/', include('hackaton.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    (r'^api/player/(?P<player>.+)/$', 'server.views.getPlayerInfo'),
    (r'^api/players/$', 'server.views.getPlayersInSurrounding'),
    (r'^api/position/$', 'server.views.updatePosition'),

    (r'^api/attack/(?P<player>.+)/$', 'server.views.attackPlayer'),
    (r'^api/attacks/initiated/$', 'server.views.getAttacksInitiated'),
    (r'^api/attacks/being_attacked/$', 'server.views.getAttacksBeingAttacked'),

    (r'^api/attacks/(?P<id>\d+)/accept/$', 'server.views.acceptAttack'),
    (r'^api/attacks/(?P<id>\d+)/dismiss/$', 'server.views.dismissAttack'),

    url(r'^admin/', include(admin.site.urls)),
)
