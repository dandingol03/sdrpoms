'use strict';

function myTool() {
	return {
		connection : null,
		localUserid : null,
		tmp : null,
		chatContainer : null,
		toolInit : function() {

			this.connection = new RTCMultiConnection();
			// var temp = $('sdasd');

			/* this.connection.socketURL = '/'; */
			this.connection.socketURL = 'https://58.56.100.10:9001/';
			// this.connection.attachStreams = {};

			// comment-out below line if you do not have your own socket.io
			// server
			// connection.socketURL =
			// 'https://rtcmulticonnection.herokuapp.com:443/';

			this.connection.socketMessageEvent = 'audio-video-file-chat-demo';

			this.connection.enableFileSharing = true; // by default, it is
														// "false".
			this.connection.onmessage = this.onMessage
			this.connection.session = {
				audio : true,
				video : true,
				data : true
			};

			this.connection.sdpConstraints.mandatory = {
				OfferToReceiveAudio : true,
				OfferToReceiveVideo : true
			};

			this.connection.onstream = function(event) {
				event.mediaElement.removeAttribute('src');
				event.mediaElement.removeAttribute('srcObject');
				// streams.push(event.stream)

				// 地图上会有默认的视频元
				var video;

				if (event.type === 'local') {
					video = $('#M');
					video.muted = true;
					video[0].srcObject = event.stream;
					setTimeout(function() {
						video[0].play();
					}, 5000);
				} else {
				    var video = document.createElement('video');
	                video.controls = true;
					$('.ex_main_video_big').append(video)
					video.srcObject = event.stream;
					setTimeout(function() {
						video.play();
					}, 5000);
					var videoList = $('.ex_main_video_big video');
					
					if(videoList.length>1){
						$('.ex_main_video_big video').css({
							'width':'200px',
							'height':'200px;',
							'float':'left'
						})
					}else if(videoList.length==1){
						$('.ex_main_video_big video').css({
							'width':'100%',
							'height':'100%;'
						})
					}
					/*video = $('#O');*/
					// video.muted = true;
					/*video[0].srcObject = event.stream*/
				}
				/* video.srcObject = event.stream; */

				// var ele = document.getElementById('videos-container');
				// var width = parseInt(ele.clientWidth / 2) - 20;
				// 获取uerid
				// try {
				// var mediaElement = getHTMLMediaElement(video[0], {
				// title: event.userid,
				// buttons: ['full-screen'],
				// width: 200,
				// showOnMouseEnter: false
				// });
				// }
				// catch (e) {
				// console.log(e);
				// }
				// //
				// ele.appendChild(mediaElement);
				

				// mediaElement.id = event.streamid;
			};

			this.connection.onstreamended = function(event) {
				var mediaElement = document.getElementById(event.streamid);
				if (mediaElement) {
					mediaElement.parentNode.removeChild(mediaElement);
				}
			};

			this.connection.onopen = function() {

			};
			// 退出会话的回调
			this.connection.onEntireSessionClosed = function(event) {

			}
		},

		CLogin : function(localUserId, lng, lat, type, isTeamMember, startDate) {
			this.connection.localUserid = localUserId;
			this.localUserid = localUserId;
			var data = {};
			data = {
				userId : localUserId,
				lng : lng,
				lat : lat,
				type : type,
				isTeamMember : isTeamMember,
				startDate : startDate
			}

			var instance = this;
			// var mData=JSON.stringify(data);
			this.connection.login(localUserId, isTeamMember, function() {
				/*
				 * instance.connection.socket.on('return-userlist', function
				 * (responseCode, data) { console.log("er");
				 * 
				 * });
				 */
				instance.connection.socket.on('receive-audio', function (message, userid) {
                    var obj_url = window.URL.createVideoUrl(new Blob([message], { type: "audio/mp3" }))
                    document.getElementById("audio").src = obj_url
                    document.getElementById("audio").play()

                });
                // 接收文本信息
                instance.connection.socket.on('receive-message', function (message, userid) {
                  console.log("接收到的聊天信息",message);

                });

                // 接收录音信息
                instance.connection.socket.on('receive-img', function (data) {
                    var mydiv = document.getElementById('file-container'); // 获得dom对象
                    var bigImg = document.createElement("img");     // 创建一个img元素
                    var oDiv = document.createElement('div');
                    document.body.appendChild(oDiv);
                    oDiv.id = "myDiv";

                    bigImg.width = "100";  // 200个像素 不用加px
                    bigImg.src = data;   // 给img元素的src属性赋值
                    mydiv.appendChild(bigImg);      // 为dom添加子元素img
                });
                //接收聊天消息
                instance.connection.socket.on('receive-message', function (message, userid) {
                    console.log("======接收到的聊天信息=====", message+userid);
                    $('.map_chat_sendMess_center').append("<div style='width:100%;text-align:left'>"+message+"</div>")
                    

                });
                //接收未读聊天消息 
                instance.connection.socket.on('receive-unread-message', function (message, userid) {
                    console.log("======接收到的聊天信息=====", message);

                });

                //接收录音信息
                instance.connection.socket.on('receive-img', function (data) {
                    var mydiv = document.getElementById('file-container'); //获得dom对象 
                    var bigImg = document.createElement("img");     //创建一个img元素 
                    var oDiv = document.createElement('div');
                    document.body.appendChild(oDiv);
                    oDiv.id = "myDiv";

                    bigImg.width = "100";  //200个像素 不用加px  
                    bigImg.src = data;   //给img元素的src属性赋值  
                    mydiv.appendChild(bigImg);      //为dom添加子元素img

                });
                //接收群消息 
                instance.connection.socket.on('receive-message-group', function (groupId, message) {
                    console.log("=========接收到群消息========" + groupId + "+++++++++++" + message)
                });
                //接收用户所在的所有群的名字
                instance.connection.socket.on('receive-group-list', function (groupId) {
                	var chatQHtml="";
					for(var i=0;i<groupId.length;i++){
						console.log('-------------------',groupId[i])
						chatQHtml+= "<div style='height:30px;line-height:30px;margin:3px 0;padding:10px;cursor:pointer;' onclick='chatSection(1,&quot;"+groupId[i]+"&quot;,2)'>"+
										"<div style='width:30px;height:30px;padding:0;margin:0;background:url(../../sdrpoms/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
										"</div>"+
										"<span class='map_chat_span'>"+groupId[i]+"</span>"+
									"</div>";
					}
					$('#chatQLeft').html(chatQHtml);
					$("#chatQLeft").mCustomScrollbar();
                    console.log("=========接收所有所在群的名称=====" + groupId )
                });
                //接受用户建群成功或失败的消息  成功：1  失败：-1  失败房间同名：2
                instance.connection.socket.on('create-group-state', function (state) {
                    console.log("=========接收到创建群成功的消息=====" + state)
                });
                //接收群成员列表信息
                instance.connection.socket.on('receive-group-userslist', function (userIds) {
                	debugger
                    console.log("=========接收到群成员列表========");
                    console.log(userIds);
                    $('.ex_map_chatMess').show();
					var chatQHtml="";
					for(var i=0;i<userIds.length;i++){
						console.log('----22222',userIds[i].user_id)
						chatQHtml+= "<div class='map_chat_div' id= "+userIds[i].user_id+" onclick='chatMessAppend(&quot;"+userIds[i].user_id+"&quot;,1,this)'>"+
										"<div style='width:30px;height:30px;padding:0;margin:0;background:url(../../sdrpoms/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
										"</div>"+
										"<span class='map_chat_span'>不知道是啥"+"</span>"+
									"</div>";
					}	
					$('.ex_map_chatMess_left_bottom').html(chatQHtml);
                });
                //接收系统信息
                instance.connection.socket.on('sys', function (groupId, message) {
                    console.log("=========接收到群列表========");
                    console.log(groupId);
                });
			});
		},
		openRoom : function(remotename) {

			console.log(remotename);

			this.connection.open(this.localUserid);
			this.connection.notify(remotename, this.localUserid);
			
			this.connection.filesContainer = document.getElementById('textDiv');
// this.chatContainer = document.getElementById('sdasd');

		},
		sendRtcMessage : function(value) {
			this.connection.send(value);
		
		},
		onMessage : function(event) {
			console.log("message got ->" + event.data)
			
		   $('#textDiv'). append("<p>"+event.data+"</p>"); 
		},
		sendFile : function(file) {
			this.connection.send(file);
		},

        sendImg: function (event, remoteUserid) {
            /**
			 * 先判断浏览器是否支持FileReader
			 */
            var instance = this;
            if (typeof FileReader === 'undefined') {
                alert('您的浏览器不支持，该更新了');
                // 使用bootstrap的样式禁用Button
                document.getElementById("imgButton").attr('disabled', 'disabled');
            } else {
                let file = event.target.files[0]; // 先得到选中的文件
                // 判断文件是否是图片
                if (!/image\/\w+/.test(file.type)) { // 如果不是图片
                    alert("请选择图片");
                    return false;
                }
                /**
				 * 然后使用FileReader读取文件
				 */
                let reader = new FileReader();
                reader.readAsDataURL(file);
                /**
				 * 读取完自动触发onload函数,我们触发sendImg事件给服务器
				 */
                reader.onload = function (e) {
                    instance.connection.socket.emit('sendImg', remoteUserid, this.result, instance.localUserid);
                }
            }

        },
        // 发送录音消息
        sendAudio: function (message, remoteUser, callback) {
            var instance = this;
            instance.connection.socket.emit('send-audio', message, remoteUser, instance.localUserid);
        },
        // 发送文本消息
//        sendMessage: function (message, remoteUser, callback) {
//            var instance = this;
//            instance.connection.socket.emit('send-message', message, remoteUser, instance.localUserid);
//        }
        /********************************************************* */
        //单人发送文本消息
        sendMessage: function (message, remoteUser, callback) {
            var instance = this;
            instance.connection.socket.emit('send-message-person', message, remoteUser, instance.connection.userid);
        },
        //用户加入群
        joinGroup: function (groupId) {
            var instance = this;
            instance.connection.socket.emit('join-group', groupId, instance.localUserid)
        },
        //在群里发送文本消息
        sendMessageGroup: function (groupId, message, sender, type) {
            var instance = this;
            instance.connection.socket.emit('send-message-group', groupId, message, sender, type);
        },
        //用户创建群
        createGroup: function (groupId, userIds) {
            var instance = this;
            instance.connection.socket.emit('create-group', groupId, instance.localUserid, userIds);
        },
        //用户退出群
        leaveGroup: function (groupId) {
            var instance = this;
            instance.connection.socket.emit('leave-group', groupId, instance.localUserid)
        },
        //获取用户所在群的列表
        getUserRoom: function (userid) {
            var instance = this;
            instance.connection.socket.emit('get-userRoom', userid);
        },
      //获取群中人员的列表
        getGroupUsers: function (groupName) {
            var instance = this;
            instance.connection.socket.emit('get-group-users', groupName);
            
            
            
        },
    }

	}

