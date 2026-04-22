variable "project_id" {
  description = "Google Cloud project ID"
  type        = string
}

variable "region" {
  description = "Google Cloud region"
  type        = string
  default     = "europe-west9"
}

variable "zone" {
  description = "Google Cloud zone"
  type        = string
  default     = "europe-west9-a"
}

variable "instance_name" {
  description = "VM instance name"
  type        = string
  default     = "ndarray-demo-vm"
}
