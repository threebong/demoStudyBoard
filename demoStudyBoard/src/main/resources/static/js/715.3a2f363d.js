"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[715],{7715:function(t,e,n){n.r(e),n.d(e,{default:function(){return p}});var o=n(3396),s=n(9242);const i={class:"board-detail"},r={class:"common-buttons"},a={class:"board-contents"},u={class:"board-contents"},d={class:"common-buttons"};function l(t,e,n,l,h,c){return(0,o.wg)(),(0,o.iD)("div",i,[(0,o._)("div",r,[(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue-gray",onClick:e[0]||(e[0]=(...t)=>c.fnSave&&c.fnSave(...t))},"저장"),(0,o.Uk)("  "),(0,o._)("button",{type:"button",class:"w3-button w3-round w3-gray",onClick:e[1]||(e[1]=(...t)=>c.fnList&&c.fnList(...t))},"목록")]),(0,o._)("div",a,[(0,o.wy)((0,o._)("input",{type:"text","onUpdate:modelValue":e[2]||(e[2]=t=>h.title=t),class:"w3-input w3-border",placeholder:"제목을 입력해주세요."},null,512),[[s.nr,h.title]]),void 0===h.idx?(0,o.wy)(((0,o.wg)(),(0,o.iD)("input",{key:0,type:"text","onUpdate:modelValue":e[3]||(e[3]=t=>h.author=t),class:"w3-input w3-border",placeholder:"작성자를 입력해주세요."},null,512)),[[s.nr,h.author]]):(0,o.kq)("",!0)]),(0,o._)("div",u,[(0,o.wy)((0,o._)("textarea",{id:"",cols:"30",rows:"10","onUpdate:modelValue":e[4]||(e[4]=t=>h.contents=t),class:"w3-input w3-border",style:{resize:"none"}},"\r\n        ",512),[[s.nr,h.contents]])]),(0,o._)("div",d,[(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue-gray",onClick:e[5]||(e[5]=(...t)=>c.fnSave&&c.fnSave(...t))},"저장"),(0,o.Uk)("  "),(0,o._)("button",{type:"button",class:"w3-button w3-round w3-gray",onClick:e[6]||(e[6]=(...t)=>c.fnList&&c.fnList(...t))},"목록")])])}n(7658);var h=n(9073),c={data(){return{requestBody:this.$route.query,idx:this.$route.query.idx,title:"",author:"",contents:"",createdAt:""}},mounted(){this.fnGetView()},methods:{fnGetView(){void 0!==this.idx&&h.Z.get("/api/board/"+this.idx).then((t=>{this.title=t.title,this.author=t.author,this.contents=t.contents,this.createdAt=t.createdAt})).catch((t=>{console.log(t)}))},fnList(){delete this.requestBody.idx,this.$router.push({path:"./list",query:this.requestBody})},fnView(t){this.requestBody.idx=t,this.$router.push({path:"./detail",query:this.requestBody})},fnSave(){let t="/api/board";this.form={idx:this.idx,title:this.title,contents:this.contents,author:this.author},void 0===this.idx?(alert(t),alert(this.form),h.Z.post(t,this.form).then((t=>{alert("글이 저장되었습니다."),this.fnView(t.idx)})).catch((t=>{t.message.indexOf("Network Error")>-1&&alert("네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.")}))):h.Z.patch(t,this.form).then((t=>{alert("글이 저장되었습니다."),this.fnView(t.idx)})).catch((t=>{t.message.indexOf("Network Error")>-1&&alert("네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.")}))}}},w=n(89);const f=(0,w.Z)(c,[["render",l]]);var p=f}}]);
//# sourceMappingURL=715.3a2f363d.js.map