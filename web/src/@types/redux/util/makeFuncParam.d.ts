export interface ApiCallSagaFunc {
  type: string;
  apiFunc: any;
  successCb?: any; // TODO: Generator인데.. Generator하면 saga call함수랑 type error ㅠ
  failCb?: any; // TODO: Generator인데.. Generator하면 saga call함수랑 type error ㅠ
}
