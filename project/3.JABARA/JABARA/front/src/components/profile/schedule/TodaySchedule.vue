<template>
  <ul class="todolist pop-scroll">
    <li v-for="schedule in upcomingSchedule" :key="schedule.id" class="task">
      <input type="checkbox" :id="schedule.id" />
      <label :for="schedule.id">{{schedule.title}}</label>
    </li>
  </ul>
</template>
<script>
export default {
  props: {
    upcomingSchedule: {
      type: Array,
      required: true
    }
  }
};
</script>
<style scpoed>
@import url(http://weloveiconfonts.com/api/?family=entypo);
.pop-scroll {
  width: 100%;
  height: 90%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.todolist {
  display: inline-block;
  vertical-align: middle;
  width: 95%;
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  font-family: "Armata", sans-serif;
  font-weight: bold;
  color: #fafafa;
  overflow: hidden;
  border: 1em solid rgba(255, 255, 255, 0.04);
  border-top-color: rgba(0, 0, 0, 0.05);
  border-bottom-color: rgba(0, 0, 0, 0.05);
}
.task > input {
  position: absolute;
  left: -9999px;
}
.task {
  display: block;
  height: 5em;
  transition: 500ms ease;
}
.task:hover {
  box-shadow: inset -30px 0 30px -20px rgba(0, 0, 0, 0.4),
    inset 30px 0 30px -20px rgba(0, 0, 0, 0.4);
}
.task > label {
  width: 100%;
  position: relative;
  cursor: pointer;
  display: block;
  height: 100%;
  background: #ff9f8e;
  font-size: 1.5em;
  line-height: 3.5em;
  text-shadow: 0 2px 0 rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05),
    inset 0 -2px 0 rgba(0, 0, 0, 0.05), inset -2px 0px 0 rgba(0, 0, 0, 0.05);
  transform-style: preserve-3d;
  perspective: 0;
  transform-origin: 150px 0 -150px;
  transition: 500ms ease;
}
.task > label:after {
  content: "\2713";
  font-family: "entypo", sans-serif;
  position: absolute;
  height: 100%;
  width: 100%;
  background: #00b5b5;
  right: -100%;
  text-shadow: 0 2px 0 rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05),
    inset 0 -2px 0 rgba(0, 0, 0, 0.05);
  transform-origin: 0 0 0;
  transform: rotateY(90deg);
}
.task > label:before {
  content: "";
  position: absolute;
  height: 100%;
  width: 30%;
  right: -15%;
  background: rgba(0, 0, 0, 0);
  box-shadow: 0 0 0 0 rgba(0, 0, 0, 0.2);
  z-index: 4;
  transform: rotateY(45deg);
  transition: 500ms ease;
}
.task:hover label:before {
  box-shadow: 0 -15px 20px -20px rgba(0, 0, 0, 0.2);
}
.task:hover label {
  transform: rotateY(-20deg);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05),
    inset 0 -2px 0 rgba(0, 0, 0, 0.05), inset -3px 0px 0 rgba(0, 0, 0, 0.05);
}
.task:hover label:after {
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05),
    inset 0 -2px 0 rgba(0, 0, 0, 0.05), inset 2px 0px 0 rgba(255, 255, 255, 0.2);
}

.task:hover input:checked + label:after {
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.05),
    inset 0 -2px 0 rgba(0, 0, 0, 0.05);
}
.task:hover input:checked + label {
  transform: rotateY(-70deg);
}

.task > input:checked + label {
  transform: rotateY(-90deg);
}
</style>