import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';

export class Api {
  private api: AxiosInstance;

  constructor(config: AxiosRequestConfig) {
    this.api = axios.create(config);
  }

  public get<T, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.api.get(url);
  }

  public post<T, D, R = AxiosResponse<T>>(url: string, data: D): Promise<R> {
    return this.api.post(url, data);
  }

  public patch<T, D, R = AxiosResponse<T>>(url: string, data: D): Promise<R> {
    return this.api.patch(url, data);
  }

  public delete<T, D, R = AxiosResponse<T>>(url: string, data: D): Promise<R> {
    return this.api.patch(url, data);
  }
}
